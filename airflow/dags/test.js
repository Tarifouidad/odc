const { uploadToCloudinary, deleteFromCloudinary, getFolderResources } = require('../Config/cloudinaryConfig');
const BeneficiaireFileUpload = require('../Models/BeneficiaireFileUpload.model');
const mongoose = require('mongoose');
const XLSX = require('xlsx');
const axios = require('axios');
const fs = require('fs');
const path = require('path');

// Upload a file to Cloudinary and save metadata
const uploadFile = async (req, res) => {
  try {
    if (!req.file) {
      return res.status(400).json({ message: 'No file uploaded' });
    }
    
    // Validate formation ID
    const formationId = req.body.formationId;
    if (!formationId || !mongoose.Types.ObjectId.isValid(formationId)) {
      return res.status(400).json({ message: 'Valid formation ID is required' });
    }
    
    console.log('Processing upload for formation:', formationId);

    // Validate Excel/CSV file and normalize columns
    try {
      const workbook = XLSX.read(req.file.buffer, { type: 'buffer' });
      if (!workbook.SheetNames.length) {
        return res.status(400).json({ message: 'The uploaded Excel file has no sheets' });
      }
      
      // Get first sheet and verify it has data
      const worksheet = workbook.Sheets[workbook.SheetNames[0]];
      const sampleData = XLSX.utils.sheet_to_json(worksheet, { range: 0, header: 1 });
      
      if (!sampleData || sampleData.length < 2) { // Assuming at least a header row and one data row
        return res.status(400).json({ message: 'The uploaded file appears to be empty or invalid' });
      }
      
      console.log('File verified, sample row count:', sampleData.length);
      
      // Normaliser les colonnes du fichier Excel pour résoudre le problème de mappage Talend
      try {
        console.log('Normalisation et correction du fichier Excel...');
        
        // Définir les colonnes standard dans l'ordre exact attendu par Talend
        const standardColumns = [
          "Horodateur", "Confirmation_Appel", "Confirmation_E_mail", "Email", 
          "Prenom", "Nom", "Genre", "Date_de_naissance", "Pays", 
          "Situation_Profetionnelle", "Profession", "Votre_age", "Telelphone", 
          "Niveau_d_etudes", "Avez_vous_une_experience_avec_la_gestion_de_projet", 
          "Votre_specialite", "Etablissement", "Avez_vous_deja_participe_au_programmes_ODC"
        ];
        
        // Obtenir toutes les données sous forme d'objets JSON
        const jsonData = XLSX.utils.sheet_to_json(worksheet);
        const headers = jsonData.length > 0 ? Object.keys(jsonData[0]) : [];
        
        console.log('En-têtes originaux:', headers);
        
        // Définir les patterns de recherche pour chaque colonne standard
        const columnPatterns = {
          "Horodateur": ["horodateur", "timestamp", "date", "heure"],
          "Confirmation_Appel": ["confirmation appel", "confirmation téléphonique", "appel"],
          "Confirmation_E_mail": ["confirmation e-mail", "confirmation email", "confirmation par email", "confirmationemail"],
          "Email": ["email", "e-mail", "courriel", "adresse email", "adresse e-mail"],
          "Prenom": ["prénom", "prenom", "firstname", "first name"],
          "Nom": ["nom", "lastname", "last name", "family name", "surname"],
          "Genre": ["genre", "sexe", "gender"],
          "Date_de_naissance": ["date de naissance", "date naissance", "birth date", "naissance"],
          "Pays": ["pays", "country", "nation"],
          "Situation_Profetionnelle": ["situation professionnelle", "situation profetionnelle", "statut pro", "emploi"],
          "Profession": ["profession", "métier", "metier", "job", "occupation"],
          "Votre_age": ["votre âge", "votre age", "age", "âge"],
          "Telelphone": ["téléphone", "telephone", "tel", "tél", "mobile", "phone"],
          "Niveau_d_etudes": ["niveau d'études", "niveau d'etudes", "niveau détudes", "niveau etudes", "niveau scolaire", "education", "etudes"],
          "Avez_vous_une_experience_avec_la_gestion_de_projet": ["expérience", "experience", "gestion de projet", "project management"],
          "Votre_specialite": ["spécialité", "specialite", "specialization", "domaine", "field"],
          "Etablissement": ["établissement", "etablissement", "institution", "school", "université"],
          "Avez_vous_deja_participe_au_programmes_ODC": ["participé", "participe", "programme", "odc", "participation"]
        };
        
        // Fonction pour nettoyer un texte pour comparaison
        const cleanText = (text) => {
          if (!text) return "";
          return text.toString().toLowerCase()
                    .normalize("NFD").replace(/[\u0300-\u036f]/g, "")
                    .replace(/[_\-\s]/g, "");
        };
        
        // Créer le mapping entre les colonnes originales et standards
        const columnMapping = {};
        
        // Pour chaque colonne standard
        standardColumns.forEach(stdCol => {
          // Trouver tous les patterns pour cette colonne
          const patterns = columnPatterns[stdCol] || [];
          let bestMatch = null;
          let bestMatchScore = 0;
          
          // Pour chaque en-tête original
          headers.forEach(origHeader => {
            // Ignorer les en-têtes déjà mappés
            if (Object.values(columnMapping).includes(origHeader)) return;
            
            const cleanHeader = cleanText(origHeader);
            
            // Vérifier si l'en-tête correspond à l'un des patterns
            for (const pattern of patterns) {
              const cleanPattern = cleanText(pattern);
              
              // Calculer un score de correspondance
              let score = 0;
              if (cleanHeader === cleanPattern) {
                score = 100; // Correspondance exacte
              } else if (cleanHeader.includes(cleanPattern)) {
                score = 75; // Le pattern est inclus dans l'en-tête
              } else if (cleanPattern.includes(cleanHeader)) {
                score = 50; // L'en-tête est inclus dans le pattern
              } else {
                // Calculer la distance de Levenshtein pour des correspondances approximatives
                const distance = levenshteinDistance(cleanHeader, cleanPattern);
                const maxLength = Math.max(cleanHeader.length, cleanPattern.length);
                if (maxLength > 0) {
                  const similarity = 1 - (distance / maxLength);
                  if (similarity > 0.7) { // 70% de similarité
                    score = similarity * 40; // Score maximum de 40 pour les correspondances approximatives
                  }
                }
              }
              
              // Mettre à jour le meilleur match
              if (score > bestMatchScore) {
                bestMatchScore = score;
                bestMatch = origHeader;
              }
            }
          });
          
          // Si un match a été trouvé avec un score suffisant
          if (bestMatch && bestMatchScore > 30) {
            columnMapping[stdCol] = bestMatch;
            console.log(`Mappage: "${stdCol}" -> "${bestMatch}" (score: ${bestMatchScore})`);
          } else {
            console.log(`Aucun match trouvé pour "${stdCol}"`);
          }
        });
        
        console.log('Mappage final:', columnMapping);
        
        // Créer un nouveau tableau de données avec la structure standardisée
        let newData = [];
        
        // Ajouter la ligne d'en-tête avec les colonnes standards
        newData.push(standardColumns);
        
        // Traiter chaque ligne de données
        jsonData.forEach(row => {
          const newRow = new Array(standardColumns.length).fill("");
          
          // Pour chaque colonne standard
          standardColumns.forEach((stdCol, index) => {
            // Si un mappage existe pour cette colonne
            if (columnMapping[stdCol]) {
              const origHeader = columnMapping[stdCol];
              if (row[origHeader] !== undefined) {
                newRow[index] = row[origHeader];
              }
            }
          });
          
          // Ajouter la ligne transformée
          newData.push(newRow);
        });
        
        // Fonction pour calculer la distance de Levenshtein (pour les correspondances approximatives)
        function levenshteinDistance(str1, str2) {
          const track = Array(str2.length + 1).fill(null).map(() => 
            Array(str1.length + 1).fill(null));
          
          for (let i = 0; i <= str1.length; i += 1) {
            track[0][i] = i;
          }
          
          for (let j = 0; j <= str2.length; j += 1) {
            track[j][0] = j;
          }
          
          for (let j = 1; j <= str2.length; j += 1) {
            for (let i = 1; i <= str1.length; i += 1) {
              const indicator = str1[i - 1] === str2[j - 1] ? 0 : 1;
              track[j][i] = Math.min(
                track[j][i - 1] + 1, // deletion
                track[j - 1][i] + 1, // insertion
                track[j - 1][i - 1] + indicator, // substitution
              );
            }
          }
          
          return track[str2.length][str1.length];
        }
        
        // Corriger les colonnes Email et Confirmation_E_mail
        // D'abord, identifier quelle valeur de confirmation est la plus fréquente
        let confirmationValues = {};
        let mostCommonConfirmValue = "Confirmé"; // Valeur par défaut

        // Compter les occurrences des différentes valeurs de confirmation
        for (let i = 1; i < newData.length; i++) {
          const confirmValue = newData[i][2]; // Index de Confirmation_E_mail
          
          // Si la valeur n'est pas un email et n'est pas vide
          if (confirmValue && typeof confirmValue === 'string' && !confirmValue.includes('@')) {
            confirmationValues[confirmValue] = (confirmationValues[confirmValue] || 0) + 1;
          }
        }

        // Trouver la valeur la plus fréquente
        let maxCount = 0;
        for (const [value, count] of Object.entries(confirmationValues)) {
          if (count > maxCount) {
            maxCount = count;
            mostCommonConfirmValue = " ";
          }
        }

        console.log(`Valeur de confirmation la plus fréquente: "${mostCommonConfirmValue}"`);

        // Maintenant, traiter chaque ligne
        for (let i = 1; i < newData.length; i++) {
          const confirmEmail = newData[i][2]; // Index de Confirmation_E_mail
          const email = newData[i][3];       // Index de Email
          
          // Si la valeur dans Confirmation_E_mail contient @, c'est probablement un email
          if (confirmEmail && typeof confirmEmail === 'string' && confirmEmail.includes('@')) {
            // Déplacer vers la colonne Email si celle-ci est vide
            if (!email || email === '') {
              newData[i][3] = confirmEmail; // Copier vers Email
            }
            
            // Remplacer par la valeur de confirmation la plus commune dans la colonne Confirmation_E_mail
            newData[i][2] = mostCommonConfirmValue;
          }
          // Si vide et que d'autres lignes ont une valeur standard, utiliser cette valeur
          else if ((!confirmEmail || confirmEmail === '') && maxCount > 0) {
            newData[i][2] = mostCommonConfirmValue;
          }
        }
        
        // Afficher des statistiques sur les données transformées
        const emailCount = newData.slice(1).filter(row => row[3] && row[3].toString().trim() !== "").length;
        const confirmEmailCount = newData.slice(1).filter(row => row[2] && row[2].toString().trim() !== "").length;
        
        console.log(`Statistiques après transformation - Emails: ${emailCount}, Confirmations Email: ${confirmEmailCount}`);
        
        // Créer une nouvelle feuille avec les données normalisées
        const newWorksheet = XLSX.utils.aoa_to_sheet(newData);
        
        // Créer un nouveau workbook et y ajouter la feuille
        const newWorkbook = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(newWorkbook, newWorksheet, "Beneficiaires");
        
        // Convertir le workbook en buffer pour le téléchargement
        req.file.buffer = XLSX.write(newWorkbook, { type: 'buffer', bookType: 'xlsx' });
        
        console.log('Fichier Excel normalisé avec succès');
      } catch (normalizationError) {
        console.error('Erreur lors de la normalisation du fichier Excel:', normalizationError);
        console.log('Utilisation du fichier original sans normalisation');
      }
    } catch (parseError) {
      console.error('Error parsing Excel file:', parseError);
      return res.status(400).json({ 
        message: 'Cannot parse the uploaded file. Please ensure it is a valid Excel or CSV file', 
        error: parseError.message 
      });
    }

    // Upload to Cloudinary with unique identifier
    const fileId = new mongoose.Types.ObjectId();
    const result = await uploadToCloudinary(req.file.buffer, {
      folder: `formations/beneficiairesExcels/${formationId}`,
      resource_type: 'auto',
      public_id: `file_${fileId}`,
      tags: ['excel', 'formation', formationId]
    });
    
    console.log('Cloudinary upload successful. Public ID:', result.public_id);
    
    // Create record in database with the file ID
    const fileUpload = new BeneficiaireFileUpload({
      _id: fileId,
      cloudinaryId: result.public_id,
      cloudinaryUrl: result.secure_url,
      cloudinaryFolder: `formations/beneficiairesExcels/${formationId}`,
      originalFilename: req.file.originalname,
      fileSize: req.file.size,
      fileType: req.file.mimetype,
      formation: formationId,
      uploadedBy: req.user.userId,
      description: req.body.description || `Uploaded on ${new Date().toLocaleString()}`,
      tags: req.body.tags ? req.body.tags.split(',') : ['excel', 'import'],
      processingStatus: 'pending'
    });
    
    await fileUpload.save();
    
    // Déclencher le job Talend via Airflow
    try {
      const airflowUrl = 'http://airflow-webserver:8080/api/v1/dags/process_excel_file/dagRuns';
      console.log(`Tentative de connexion à Airflow via: ${airflowUrl}`);
      
      const dagRunId = `import_file_${fileId}_${Date.now()}`;
      const payload = {
        dag_run_id: dagRunId,
        conf: {
          input_file: result.secure_url,
          formation_id: formationId,
          file_id: fileId.toString(),
          original_filename: req.file.originalname
        }
      };
      
      console.log(`Envoi de la requête à Airflow avec payload:`, payload);
      
      const airflowResponse = await axios.post(
        airflowUrl,
        payload,
        {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Basic ' + Buffer.from('admin:admin').toString('base64')
          },
          timeout: 10000
        }
      );
      
      console.log(`Réponse d'Airflow: Status=${airflowResponse.status}`);
      
      fileUpload.processingStatus = 'processing';
      fileUpload.processingStartedAt = new Date();
      fileUpload.airflowDagRunId = dagRunId;
      await fileUpload.save();
      
    } catch (airflowError) {
      console.error(`Erreur lors de la connexion à Airflow:`, airflowError);
      
      fileUpload.processingStatus = 'error';
      fileUpload.processingError = `Erreur de connexion Airflow: ${airflowError.message}`;
      await fileUpload.save();
    }

    // Renvoyer la réponse au client
    res.status(201).json({
      message: "File uploaded successfully and processing started",
      fileUpload: {
        id: fileUpload._id,
        filename: fileUpload.originalFilename,
        url: fileUpload.cloudinaryUrl,
        uploadDate: fileUpload.createdAt,
        processingStatus: fileUpload.processingStatus
      }
    });
  } catch (error) {
    console.error('Error uploading file:', error);
    res.status(500).json({ 
      message: "Error uploading file",
      error: error.message,
      details: error.http_code ? `HTTP ${error.http_code}` : undefined
    });
  }
};

// Endpoint pour mettre à jour le statut de traitement
const updateProcessingStatus = async (req, res) => {
  try {
    const { fileId } = req.params;
    const { status, result, error } = req.body;
    
    if (!mongoose.Types.ObjectId.isValid(fileId)) {
      return res.status(400).json({ message: 'Invalid file ID' });
    }
    
    const file = await BeneficiaireFileUpload.findById(fileId);
    
    if (!file) {
      return res.status(404).json({ message: 'File not found' });
    }
    
    // Mettre à jour le statut et les informations associées
    file.processingStatus = status;
    file.processingCompletedAt = new Date();
    
    if (status === 'completed' && result) {
      file.processedFileUrl = result.outputFileUrl;
      file.processedFileName = result.outputFileName || `output_file_${fileId}.xlsx`;
      file.processingSummary = result.summary;
    } else if (status === 'error' && error) {
      file.processingError = error;
    }
    
    await file.save();
    
    res.status(200).json({
      message: "Processing status updated successfully",
      file: {
        id: file._id,
        filename: file.originalFilename,
        processingStatus: file.processingStatus,
        processedFileName: file.processedFileName
      }
    });
  } catch (error) {
    console.error('Error updating processing status:', error);
    res.status(500).json({ message: "Error updating processing status", error: error.message });
  }
};

// Endpoint pour télécharger le fichier traité
const downloadProcessedFile = async (req, res) => {
  try {
    const { fileId } = req.params;
    
    if (!mongoose.Types.ObjectId.isValid(fileId)) {
      return res.status(400).json({ message: 'Invalid file ID' });
    }
    
    const file = await BeneficiaireFileUpload.findById(fileId);
    
    if (!file) {
      return res.status(404).json({ message: 'File not found' });
    }
    
    if (file.processingStatus !== 'completed') {
      return res.status(400).json({ 
        message: 'Processed file not available', 
        status: file.processingStatus 
      });
    }
    
    // Chemin du fichier de sortie avec l'ID unique
    const outputFileName = file.processedFileName || `output_file_${fileId}.xlsx`;
    const possiblePaths = [
      `/opt/airflow/talend_jobs/output/${outputFileName}`,
      `/jobs/output/${outputFileName}`,
      `/opt/airflow/talend_jobs/CorrigeAge/CorrigeAge/outdataset.xlsx`,
      `/opt/airflow/talend_jobs/JobETL/JobETL/outdataset.xlsx`
    ];
    
    // Chercher le fichier dans les emplacements possibles
    let filePath = null;
    for (const currentPath of possiblePaths) {
      if (fs.existsSync(currentPath)) {
        filePath = currentPath;
        break;
      }
    }
    
    if (!filePath) {
      return res.status(404).json({ message: 'Processed file not found on server' });
    }
    
    // Envoyer le fichier
    res.setHeader('Content-Disposition', `attachment; filename="processed_${file.originalFilename}"`);
    res.setHeader('Content-Type', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');
    
    const fileStream = fs.createReadStream(filePath);
    fileStream.pipe(res);
  } catch (error) {
    console.error('Error downloading processed file:', error);
    res.status(500).json({ message: "Error downloading processed file", error: error.message });
  }
};

// Get all files for a formation
const getFormationFiles = async (req, res) => {
  try {
    const { formationId } = req.params;
    
    if (!mongoose.Types.ObjectId.isValid(formationId)) {
      return res.status(400).json({ message: 'Invalid formation ID' });
    }
    
    const files = await BeneficiaireFileUpload.find({ formation: formationId })
      .sort({ createdAt: -1 }) // Most recent first
      .populate('uploadedBy', 'nom prenom');
      
    res.status(200).json({
      message: "Files retrieved successfully",
      count: files.length,
      files
    });
  } catch (error) {
    console.error('Error retrieving files:', error);
    res.status(500).json({ message: "Error retrieving files", error: error.message });
  }
};

// Get a single file by ID
const getFileById = async (req, res) => {
  try {
    const { id } = req.params;
    
    if (!mongoose.Types.ObjectId.isValid(id)) {
      return res.status(400).json({ message: 'Invalid file ID' });
    }
    
    const file = await BeneficiaireFileUpload.findById(id)
      .populate('uploadedBy', 'nom prenom')
      .populate('formation', 'nom dateDebut dateFin');
      
    if (!file) {
      return res.status(404).json({ message: 'File not found' });
    }
    
    res.status(200).json({
      message: "File retrieved successfully",
      file
    });
  } catch (error) {
    console.error('Error retrieving file:', error);
    res.status(500).json({ message: "Error retrieving file", error: error.message });
  }
};

// Delete a file
const deleteFile = async (req, res) => {
  try {
    const { id } = req.params;
    
    if (!mongoose.Types.ObjectId.isValid(id)) {
      return res.status(400).json({ message: 'Invalid file ID' });
    }
    
    const file = await BeneficiaireFileUpload.findById(id);
    
    if (!file) {
      return res.status(404).json({ message: 'File not found' });
    }
    
    // Check permission
    if (!req.user.role === 'Admin' && file.uploadedBy.toString() !== req.user.userId) {
      return res.status(403).json({ message: 'Permission denied. You can only delete files you uploaded.' });
    }
    
    // Delete from Cloudinary
    if (file.cloudinaryId) {
      await deleteFromCloudinary(file.cloudinaryId);
    }
    
    // Delete from database
    await BeneficiaireFileUpload.findByIdAndDelete(id);
    
    res.status(200).json({
      message: "File deleted successfully",
      deletedFileId: id
    });
  } catch (error) {
    console.error('Error deleting file:', error);
    res.status(500).json({ message: "Error deleting file", error: error.message });
  }
};

// Update file metadata
const updateFileMetadata = async (req, res) => {
  try {
    const { id } = req.params;
    const { description, tags } = req.body;
    
    if (!mongoose.Types.ObjectId.isValid(id)) {
      return res.status(400).json({ message: 'Invalid file ID' });
    }
    
    const file = await BeneficiaireFileUpload.findById(id);
    
    if (!file) {
      return res.status(404).json({ message: 'File not found' });
    }
    
    // Update fields
    const updateData = {};
    if (description) updateData.description = description;
    if (tags) updateData.tags = typeof tags === 'string' ? tags.split(',') : tags;
    
    const updatedFile = await BeneficiaireFileUpload.findByIdAndUpdate(
      id,
      updateData,
      { new: true }
    );
    
    res.status(200).json({
      message: "File metadata updated successfully",
      file: updatedFile
    });
  } catch (error) {
    console.error('Error updating file metadata:', error);
    res.status(500).json({ message: "Error updating file metadata", error: error.message });
  }
};

module.exports = {
  uploadFile,
  getFormationFiles,
  getFileById,
  deleteFile,
  updateFileMetadata,
  updateProcessingStatus,
  downloadProcessedFile
};
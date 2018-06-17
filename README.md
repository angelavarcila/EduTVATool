# EduTVATool
Source code of EduTVATool (Tool for marking up audiovisual educational content)

You have to add and configure the following system variables in the table "vrbl_sstm" of data base:

| nmbr_vrbl_sstm | vlr_vrbl_sstm |

| ----- | ---- |

| CONTENIDO COLUMNA 1 | CONTENIDO COLUMNA 2 |

- url_edutva -> application deployment url, e.g. http://10.10.10.10:8080/EduTVA
- url_videos -> url where the videos are hosted, e.g http://www.urlvideos.co/VideosEduTVA
- url_dscrpcn -> path where the user descriptor files will be stored, e.g /home/path/descriptorsfiles
- CRID -> content reference identifier
- ruta_ClassificationScheme -> directory path that contains the classification schemes files used by the tool
- ruta_xsd -> path where the .xsd EduTVA file is located  /home/pathedutvav1/tva2v161_edutva_v1.xsd 
	
Name of Classification Scheme Files:
- intended_educational_user -> e.g. IntendedEducationalUserCS_es.xml
- educational_use
- role
- content
- place
- semantic_density
- educational_context
- ability
- interactivity_type
- educational_audience
- age_range
- file_format

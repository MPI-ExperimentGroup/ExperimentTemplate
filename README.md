Frinex: Framework for Interactive Experiments
=================

Scientific experiment software under development, 
Peter Withers, peter.withers@mpi.nl, 
Experiment Group, MPI Nijmegen. 

[![DOI](https://zenodo.org/badge/37583935.svg)](https://zenodo.org/badge/latestdoi/37583935)

# Frinex Goals
* Provide software for interactive scientific experiments
* Separate the experiment design from the software implementation.
* Allow reuse across experiments, eg stimulus or activities or metadata.
* Prevent the need for continuous redevelopment of the same or similar experiment software.
* Make the individual experiment applications available to the researchers so that re runs and post publication experiment validation are possible.
* Provide experiments on mobile devices and via the web.
* Modularise development so that components can be added / changed / replaced.

# Publications
Withers, P. (2016). Frinex: Framework for Interactive Experiments. Poster presented at the IT-Verantwortlichen-Treffen, Berlin, Germany.
https://pure.mpg.de/rest/items/item_2353700_1/component/file_2353699/content

# Templates
The current template is based on [SynQuiz](https://github.com/languageininteraction/GraphemeColourSynaesthesiaApp) and [LingQuest](https://github.com/languageininteraction/LanguageMemoryApp), which are iOS and Android applications developed in the Language In Interaction 3 project. These apps are already in the various app stores.

This template produces: 
* Web experiments
* iOS experiments
* Android experiments
* Desktop experiments
* Can produce other platforms, facebook, desktop, wince …

Other templates can be developed as needed:
* Native iOS
* Native Android
* Unity3D?
* Minecraft?
* Chrome apps?

# System Overview
( [UML diagram](https://github.com/MPI-ExperimentGroup/ExperimentTemplate/blob/master/src/main/uml/Frinex.svg) )

1. Experiment designer interface
   * Configure: screens, metadata, stimulus…

2. Experiment application templates
   * Templates can be created in different technologies
   * This is the code that becomes the experiment app

3. Compilation process

4. Compiled experiment
   * Self contained application, mobile / web

5. Experiment results administrator
   * Viewing and downloading experiment results
   * Managing participants

# Experiment Descriptor XML
Each experiment is defined in an experiment description XML file which is used to compile the web, mobile, desktop versions of the experiment. The compiled experiment software consists of a standalone server side and client side applications that can be deployed by the build system or deployed independently.

( [HTML Documentation](https://raw.githubusercontent.com/MPI-ExperimentGroup/ExperimentTemplate/master/ExperimentDesigner/src/test/resources/frinex-rest-output/frinex.html) )
https://htmlpreview.github.io/?https://github.com/MPI-ExperimentGroup/ExperimentTemplate/blob/master/ExperimentDesigner/src/test/resources/frinex-rest-output/frinex.html


( [XSD Stylesheet](https://github.com/MPI-ExperimentGroup/ExperimentTemplate/blob/master/ExperimentDesigner/src/test/resources/frinex-rest-output/frinex.xsd) )
https://raw.githubusercontent.com/MPI-ExperimentGroup/ExperimentTemplate/master/ExperimentDesigner/src/test/resources/frinex-rest-output/frinex.xsd

# Automated Build Service
( [UML diagram](https://github.com/MPI-ExperimentGroup/ExperimentTemplate/blob/master/src/main/uml/BuildServer.svg) )
Experiments can be built using the automated build service. Any stimuli files also need to be committed alongside the JSON or XML in a directory of the same name. To use this service you either need to be given access to a relevant GIT repository where you can commit your JSON or XML configuration files. Or you can install and manage an instance of the build service on your local network.

( [FrinexBuildService](https://github.com/MPI-ExperimentGroup/FrinexBuildService) )
https://github.com/MPI-ExperimentGroup/FrinexBuildService

# Submodules    

Submodules can exist within a template when the  technologies are compatible:
* Elements of the DOBES annotator prototype have been included in the system and could be used to collect and display time aligned annotations
* Elements of [KinOath](https://github.com/KinshipSoftware/KinOathKinshipArchiver) such as kintype diagrams could be included to allow for the collection and annotation of kinship data
* The WAV recorder and CSV writer from FieldKit has already been included as a submodule

(The DOBES annotator prototype was developed by the TLA but not published)

(FieldKit was developed by the TG but not published)

# Getting the results

Web based results
* Download zip file of CSV output
* Direct query with JSON output, eg via R (planned)

Offline SD card (mobile apps only)
* CSV time aligned output
* Audio/video recordings
* Post processing in ELAN

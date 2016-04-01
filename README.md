Frinex: Framework for Interactive Experiments
=================

Scientific experiment software under development, 
Peter Withers, peter.withers@mpi.nl, 
Experiment Group, MPI Nijmegen. 

# Frinex Goals
* Provide software for interactive scientific experiments
* Separate the experiment design from the software implementation.
* Allow reuse across experiments, eg stimulus or activities or metadata.
* Prevent the need for continuous redevelopment of the same or similar experiment software.
* Make the individual experiment applications available to the researchers so that re runs and post publication experiment validation are possible.
* Provide experiments on mobile devices and via the web.
* Modularise development so that components can be added / changed / replaced.

# Templates
The current template is based on [SynQuiz](https://github.com/languageininteraction/GraphemeColourSynaesthesiaApp) and [LingQuest](https://github.com/languageininteraction/LanguageMemoryApp), which are iOS and Android applications developed in the Language In Interaction 3 project. These apps are already in the various app stores.

This template produces: 
* Web experiments
* iOS experiments
* Android experiments
* Can produce other platforms, facebook, desktop, wince …

Other templates can be developed as needed:
* Native iOS
* Native Android
* Unity3D?
* Minecraft?
* Chrome apps?

![System Overview](https://raw.githubusercontent.com/MPI-ExperimentGroup/ExperimentTemplate/master/src/main/uml/Frinex.png)
System Overview
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

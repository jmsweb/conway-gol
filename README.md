# Conway Game of Life (Java)
---
## Prerequisites

- Java 9
- JavaFX
  - The package should already included in Java9 installation.
- Maven 3.3.9
- JavaFX SceneBuilder 9(Optional)

## Installation

Java and Maven knowledge is needed to execute this standalone application. SceneBuilder is not used to run this Java application but the software was helpful to visualize the layout in wireframe, quick lookups to FXML `Node` attributes and properties, and read/write to FXML file.

1. Clone `conway-gol` repository
2. Change in `conway-gol` directory
3. Run `mvn clean install` command
   - This command will download project dependencies from repo.maven.apache.org site, compile and run tests, and build to package the application as executable jar all-at-once. You will see application booting up and robot performing couple of mouse click tasks. That is the `test` phase in Maven process. After this, a `target` folder is generated.
4. Execute either of the following command from a terminal:
   - `mvn exec:java -Dexec.mainClass=com.cnb.conway.App`
   - `java -jar target/conway-gol.jar`
5. Execute `mvn test` to run test cases.

## Usage

The application is simple because the UI contains 4 UI components: 3 Buttons and a Slider. It is not resizable, and exiting application can be done by clicking on exit button top-right corner of window.

### Initial State

Slider, Randomize, and Start Generation are enabled. Stop Population is disabled. The scene display a white background. Nothing special.

### Generation State

To view Conway Game of Life simulation, please populate scene with clicking on the Randomize button as this will determine probability of a empty cell being alive or dead for entire grid. The button can be clicked as many times to get desired initial population. After an initial population is found, Start Population button can click to start produce generations.

Slider and Stop Population are enabled. Randomize and Start Generation are disabled. Use slider to decrease or increase generation speed.

### Stopped State

Slider, Randomize, and Start Generation are enabled. Stop Population is disabled. The scene show a snapshot of generation frozen in time. Randomize button will erase and re-seed initial population. Start Generation button will resume the generating process.

## Future Development
If given extra time, I would include features such as Zoom, Draw Own Pattern, Sizing and Scaling, Colorize Cell, and Display Grid Line. There are more to this than meets the eye. It is interesting to view shapes and patterns of different kinds in this simulation that piqued my curiosity to contemplate ideas and features for future development.

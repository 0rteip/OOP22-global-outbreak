package globaloutbreak.view.scenecontroller;

    import java.util.ArrayList;
    import java.util.List;

import org.slf4j.Logger;
    

    import javafx.fxml.FXML;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javafx.scene.input.MouseEvent;
    import javafx.scene.layout.GridPane;
    import javafx.stage.Stage;

    /**
     * Class that manage button handlers.
     */
   public class MutationViewController extends AbstractSceneController implements SceneInitializer {
    
        @FXML
        private Button newGameButton;
    
        @FXML
        private Button tutorialButton;
    
        @FXML
        private GridPane buttonGridPane; 

        @FXML
        private GridPane buttonGridPanell; 
        
        @FXML
        private Button exitButton;
    
        @FXML
        private Button submitButton;
    
        @FXML
        private Label descriptionLabel;
    
        @FXML
        private Button actionButton;
        
        private Stage stage;    
        private Logger logger;
        private List<String> names;
        /**
         * Initialize.
         */
        @Override
        public void initializeScene() { 
            this.getView().getController().displayMutation();
            displayButton(this.getView().getMutations());
        }

        /**
         * display mutation button
         * @param list
         */
        public void displayButton(List<String>nameList){
            int rowIndex = 0;
            int columnIndex = 0;
            names = nameList;
            for (int i = 0; i < nameList.size(); i++) {
                Button button = createButton(nameList.get(i),i);             
                buttonGridPane.add(button, columnIndex, rowIndex);  
                columnIndex++;
                if (columnIndex == 4) { 
                    columnIndex = 0;
                    rowIndex++;
                }
            }
        }

        private Button createButton(String buttonText, int index) {
            Button button = new Button(buttonText);
            button.setOnAction(e -> handleButtonAction(buttonText, index));
            return button;
        }

        private Button createButtonActivate(String buttonText, int index) {
            Button button = new Button(buttonText);
            button.setOnAction(e -> handleActionButtonAction( index));
            return button;
        }
        private void handleButtonAction(String name, int index) {
            this.getView().getController().displayMutationDesc(name);
            descriptionLabel.setText(this.getView().getDescription());
            System.out.println(this.getView().checkactivate());
            if(!this.getView().checkactivate()){
                Button button =  createButtonActivate("Evolvi", index);
                buttonGridPanell.add(button, 0, 0);
            }else {
                Button button =  createButtonActivate("Involvi", index);
                buttonGridPanell.add(button, 0, 0);
            }
         //   actionButton.setOnAction(e -> handleActionButtonAction(index));

        }
        private void handleActionButtonAction( int index) {
            this.getView().getController().update(names.get(index));
            System.out.println("EVOLVI");
            // Logica da eseguire quando il pulsante dell'azione viene premuto per l'oggetto specifico
        }
        
        /**
         * Go to the previous scene.
         * 
         * @param evt event handler
         */
        @FXML
        public final void backScene(final MouseEvent evt) {       
            this.getSceneManager().openBackScene();
        }   
    
    }
    




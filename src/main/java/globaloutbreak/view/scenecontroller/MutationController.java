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
   public class MutationController extends AbstractSceneController {
    
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
    
        /**
         * Initialize logger. dal menu viene chiamato un metodo del contoller che chiama la inizialize
         */
        public final void initialize(List<String> list) { 
           // this.logger = getLogger();
           
            int rowIndex = 0;
            int columnIndex = 0;
            
            for (int i = 0; i < list.size(); i++) {
               // Item item = items.get(i);
                Button button = createButton(list.get(i),i);
    
                buttonGridPane.add(button, columnIndex, rowIndex);
    
                columnIndex++;
                if (columnIndex == 4) { // Imposta il numero di colonne desiderato
                    columnIndex = 0;
                    rowIndex++;
                }
            }
        }
        


        private Button createButton(String buttonText, int index) {
            Button button = new Button(buttonText);
            button.setOnAction(e -> handleButtonAction(index));
            return button;
        }
        private Button createButtonAc(String buttonText, int index) {
            Button button = new Button(buttonText);
            button.setOnAction(e -> handleActionButtonAction(index));
            return button;
        }
        private void handleButtonAction(int index) {
          //  Item item = items.get(index);
          
            descriptionLabel.setText("DESCRIZIONE POTENZIAMENTO"+String.valueOf(index));
             System.out.println("DESCRIZIONE POTENZIAMENTO");
         //   actionButton.setOnAction(e -> handleActionButtonAction(index));
         Button button =  createButtonAc("Evolvi", index);

            buttonGridPanell.add(button, 0, 0);

        }
        private void handleActionButtonAction(int index) {
           // Item item = items.get(index);
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
            this.stage = this.stage == null ? this.getStage(evt) : this.stage;
    
            this.getSceneManager().openBackScene(stage);
        }
    
    
    }
    




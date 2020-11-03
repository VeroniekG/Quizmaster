package model;

import javafx.scene.control.Alert;

/**
 * ApplicationAlert Model Class. Creates alerts, extends {@link Alert} and cannot be instantiated
 * directly.
 * To create an alert, create a new instance of the nested builder class and call the appropriate
 * methods on this instance to set alert properties. Calling {@link Builder#build()} creates the
 * alert. Calling {@link Alert#show()} (or {@link Alert#showAndWait()} for alerts of type
 * {@link javafx.scene.control.Alert.AlertType#CONFIRMATION}, returns Optional<ButtonType>) shows
 * the alert.
 *
 * @author Daniel Leertouwer
 * @version 1.0.0
 * @see Alert
 * @see javafx.scene.control.Alert.AlertType
 * @see Builder
 * @since 1.0
 */
public class ApplicationAlert extends Alert {

    private static final String ALERT_TITLE = "Meldingvenster";

    private ApplicationAlert(AlertType type) {
        super(type);
    }

    public static class Builder {

        private final String title;
        private Alert.AlertType type;
        private String headerText;
        private String contentText;

        public Builder() {
            this.title = ALERT_TITLE;
        }

        public Builder withAlertType(Alert.AlertType type) {
            this.type = type;
            return this;
        }

        // --Commented out by Inspection START (02/11/2020, 22:27):
        //        public Builder withTitle(String title) {
        //            this.title = title;
        //            return this;
        //        }
        // --Commented out by Inspection STOP (02/11/2020, 22:27)

        public Builder withHeaderText(String headerText) {
            this.headerText = headerText;
            return this;
        }

        public Builder withContentText(String contentText) {
            this.contentText = contentText;
            return this;
        }

        public ApplicationAlert build() {
            ApplicationAlert alert = new ApplicationAlert(this.type);
            alert.setTitle(this.title);
            alert.setHeaderText(this.headerText);
            alert.setContentText(this.contentText);
            return alert;
        }

    }

}
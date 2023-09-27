package ada.cielo.prospects.model.schemas;

import org.springframework.web.bind.annotation.ResponseStatus;

public class ResponseSchema {

    private String status;
    private String message;
    private Object data;

    public ResponseSchema(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
    public Object getData() {
        return data;
    }
}

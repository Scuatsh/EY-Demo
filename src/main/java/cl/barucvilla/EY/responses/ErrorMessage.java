package cl.barucvilla.EY.responses;

public class ErrorMessage {
    private String mensaje;

    public ErrorMessage(String message) {
        this.mensaje = message;
    }

    public String getMessage() {
        return mensaje;
    }

    public void setMessage(String message) {
        this.mensaje = message;
    }
}

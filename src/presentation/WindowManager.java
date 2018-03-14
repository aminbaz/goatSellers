package presentation;
import business_logic.models.User;
import javafx.scene.Scene;

public interface WindowManager {
	
	public abstract void handleMessageFromFrame(String message);
	
	public abstract void setUser(User user);
	
	public abstract void updateStage(Scene scene);
}

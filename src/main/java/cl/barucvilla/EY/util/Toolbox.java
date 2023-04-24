package cl.barucvilla.EY.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class Toolbox {

	public boolean validateEmail(String email) {
		String patron = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		Pattern pattern = Pattern.compile(patron);
		Matcher matcher = pattern.matcher(email);
		boolean isValid = matcher.matches();
		return isValid;
	}

	public boolean validatePassword(String password) {
		String patron = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d.*\\d)[A-Za-z\\d]+$";
		Pattern pattern = Pattern.compile(patron);
		Matcher matcher = pattern.matcher(password);
		boolean isValid = matcher.matches();
		return isValid;
	}

}

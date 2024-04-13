
package utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;

import com.github.javafaker.Faker;

public class DataHelper {
	private Locale local = new Locale("en");
	private Faker faker = new Faker(local);

	public static DataHelper getDataHelper() {
		return new DataHelper();
	}

	public String getFirstname() {
		return faker.name().firstName();
	}

	public String getLastname() {
		return faker.name().lastName();
	}

	public String getFullname() {
		String fullName = faker.name().fullName();
		return removeSpecialCharacters(removePrefix(fullName));
	}

	/**
	 * Remove common prefixes from a full name.
	 *
	 * @param fullName The full name with potential prefixes.
	 * @return The cleaned name with prefixes removed.
	 */
	public static String removePrefix(String fullName) {
		String[] prefixes = { "Dr.", "Mr.", "Mrs.", "Ms.", "Prof.", "Professor", "Rev.", "Reverend", "Hon.", "Honorable" };

		// Remove prefix if the name starts with any prefix in the list
		for (String prefix : prefixes) {
			if (fullName.startsWith(prefix)) {
				return fullName.replaceFirst("^" + prefix + "\\s*", "");
			}
		}
		return fullName;
	}

	public static String removeSpecialCharacters(String input) {
		String regex = "[^a-zA-Z0-9 ]";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(input).replaceAll("");
	}

	public String getGenderMaleAndFemale() {
		if (new Random().nextInt(2) == 0)
			return "Female";
		else
			return "Male";
	}

	public String getEmailAddress() {
		return faker.internet().emailAddress();
	}

	public String getEmailAddressByRandomNumber() {
		return "auto" + getRandomNumberByDateTime() + "@gmail.com";
	}

	public static long getRandomNumberByDateTime() {
		return Calendar.getInstance().getTimeInMillis() % 100000;
	}

	public String getPassword() {
		return faker.internet().password(8, 12, true, true);
	}

	public String getCityName() {
		return faker.address().cityName();
	}

	public String getPhone() {
		String phoneNumber = faker.phoneNumber().phoneNumber();
		// Loại bỏ các kí tự không phải là chữ số
		String cleanedPhoneNumber = phoneNumber.replaceAll("\\D", "");

		// Giữ lại 10 chữ số đầu tiên
		String finalPhoneNumber = cleanedPhoneNumber.substring(0, Math.min(cleanedPhoneNumber.length(), 10));

		return String.format("%s%s%s", finalPhoneNumber.substring(0, 3), finalPhoneNumber.substring(3, 6), finalPhoneNumber.substring(6));
	}

	public String getAddress() {
		return faker.address().streetAddress();
	}

	public String getState() {
		return faker.address().state();
	}

	public String getZipCode() {
		return faker.address().zipCodeByState(faker.address().state());
	}

	public String getPINCode() {
		Random random = new Random();
		int randomNumber = 100000 + random.nextInt(900000);
		return String.valueOf(randomNumber);
	}

	public String getZipCodeByState() {
		return faker.address().zipCode();
	}

	public String getCapital() {
		return faker.country().capital();
	}

	public String getCompanyName() {
		return faker.company().name();
	}

	public String getCreditCardNumber() {
		return faker.business().creditCardNumber();
	}

	/**
	 * Date Format Characters:<br>
	 * One letter : - d: Day of the month (1-31) - M: Month of the year (1-12) <br>
	 * Two letters : - dd: Day of the month (01-31) - MM: Month of the year (December 1) - yy: Year with two digits (00-99) <br>
	 * Three letters: - MMM: Month of the year (abbreviated)(Dec) - EEE: Day of the week (abbreviated)(Sun) <br>
	 * Four letters : - MMMM: Month of the year (full) - EEEE: Day of the week (full)(Monday) - yyyy: Year with four digits (e.g., 2023) <br>
	 * 
	 * @param fomartDate : e.g. MM/dd/yyyy
	 * @return
	 */
	public String getBirthDay(String fomartDate) {
		return new SimpleDateFormat(fomartDate).format(faker.date().birthday());
	}

	private String transferMonthNumberToChacraceter(String monthNumberString) {
		switch (monthNumberString) {
		case "01":
			return "January";
		case "02":
			return "February";
		case "03":
			return "March";
		case "04":
			return "April";
		case "05":
			return "May";
		case "06":
			return "June";
		case "07":
			return "July";
		case "08":
			return "August";
		case "09":
			return "September";
		case "10":
			return "October";
		case "11":
			return "November";
		case "12":
			return "December";

		default:
			throw new IllegalArgumentException("NumberString of month is not supported");
		}
	}

	public String getTimeDay() {
		String day = getBirthDay("MM/dd/yyyy").split("/", 3)[1];
		return day.startsWith("0") ? day.substring(1) : day;
	}

	public String getTimeMonthChacrater() {
		return transferMonthNumberToChacraceter(getBirthDay("MM/dd/yyyy").split("/", 3)[0]);
	}

	public String getTimeYear() {
		return getBirthDay("MM/dd/yyyy").split("/", 3)[2];
	}

}

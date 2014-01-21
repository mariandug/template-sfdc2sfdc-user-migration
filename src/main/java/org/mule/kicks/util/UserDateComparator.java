package org.mule.kicks.util;

import java.util.Map;

import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * The function of this class is to establish a relation happens before between
 * two maps representing SFDC users.
 * 
 * It's assumed that these maps are well formed maps from SFDC thus they both
 * contain an entry with the expected key. Never the less validations are being
 * done.
 * 
 * @author damiansima
 */
public class UserDateComparator {
	private static final String LAST_MODIFIED_DATE = "LastModifiedDate";

	/**
	 * Validate which user has the latest last modification date.
	 * 
	 * @param userA
	 *            SFDC user map
	 * @param userB
	 *            SFDC user map
	 * @return true if the last modified date from userA is after the one
	 *         from user B
	 */
	public static boolean isAfter(Map<String, String> userA, Map<String, String> userB) {
		Validate.notNull(userA, "The user A should not be null");
		Validate.notNull(userB, "The user B should not be null");

		Validate.isTrue(userA.containsKey(LAST_MODIFIED_DATE), "The user A map should containt the key " + LAST_MODIFIED_DATE);
		Validate.isTrue(userB.containsKey(LAST_MODIFIED_DATE), "The user B map should containt the key " + LAST_MODIFIED_DATE);

		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		DateTime lastModifiedDateOfA = formatter.parseDateTime(userA.get(LAST_MODIFIED_DATE));
		DateTime lastModifiedDateOfB = formatter.parseDateTime(userB.get(LAST_MODIFIED_DATE));

		return lastModifiedDateOfA.isAfter(lastModifiedDateOfB);
	}
}

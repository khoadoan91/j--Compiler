/**
 * 
 */
package pass;

import java.lang.System;
/**
 * @author KyleD
 *
 */
public class SwitchStatement {
	
	public String print1ToUsingSwitch(int x) {
		String result = "";
		switch (x) {
		case 1:
			result += "1";
		case 2:
			result += "2";
		case 3:
			result += "3";
		case 4:
			result += "4";
		default:
			result += String.valueOf(x);
		}
		return result;
	}
}

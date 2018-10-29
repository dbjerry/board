package kr.or.ddit.util;

public class StringUtil {
	
	/**
	 * Method : getFileName
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param contentDisposition --> (.getHeader("Content-disposition"))
	 * @return
	 * Method 설명 : "파일명.확장자" 구하는 메서드
	 * String contentDisposition = "form-data; name=\"profile\"; filename=\"파일명.확장자\""
	 */
	public static String getFileName(String contentDisposition){
		
		String fileName = "";
		String[] splits = contentDisposition.split("; ");

		for(String str : splits){
		
			if(str.indexOf("filename=") >= 0){
			
				fileName = str.substring(10, str.lastIndexOf("\""));
			
			}
		}
		return fileName;

	}

	/**
	 * Method : getCookie
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param cookiesString
	 * @return
	 * Method 설명 : Cookie의 value값 추출
	 * String cookieString = remember=Y; userId=brown; etc=test
	 */
	public static String getCookie(String cookieString, String cookieName) {
		String result = "";
		String[] splits = cookieString.split("; ");
		
		for (String str : splits){
			if(str.indexOf(cookieName + "=") >= 0){
				result = str.substring(str.lastIndexOf("=")+1);
			}
		}
		return result;
	}
	
}

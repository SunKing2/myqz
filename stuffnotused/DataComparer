package myqz;

import java.lang.reflect.Field;

public class DataComparer {

	public static void main(String[] args) throws Exception {
		String sExpected = 
				"$gQCount	'8'	\n" + 
				"@gFileNum_Dirty	[ '0' ]	\n" + 
				"@gFileNum_FileName	[ 'mystuff.qz' ]	\n" + 
				"@gFileNum_QuestionCount	[ '8' ]	\n" + 
				"";
		QzToJava qz = new QzToJava();
		qz.loadData();
		DataComparer.verifyFields(qz, sExpected);
	}
	public static boolean verifyFields(QzToJava qz, String fieldData) throws Exception {
		
		boolean bReturn = true;
		
		String[] lines = fieldData.split("\\n");
		
		Class<? extends QzToJava> myclass = qz.getClass();
		System.out.println("myclass=" + myclass);
		
		for (String line: lines) {
			String[] columns = p2j(line).split("\\t");
			
			String fieldName = columns[0];
			String value = columns[1];
			System.out.println("fieldName:" + fieldName);
			System.out.println("    value:" + value);

			Field fq = myclass.getDeclaredField(fieldName);
			Object val = fq.get(qz);
			System.out.println("val=" + val.toString());
			
			boolean bEqual = value.equals(val.toString());
			System.out.println(bEqual);
			
			bReturn = bReturn && bEqual;
			
			if (!bEqual) throw new Exception("field:" + fieldName + ": string:" + value + ", class:" + val.toString());
		}
		System.out.println("returning:" + bReturn);
		return bReturn;
	}
	private static String p2j(String column) {
		column = column.replace("$g", "");
		column = column.replace("@g", "");
		column = column.replace("_", "");
		column = column.replace("', '", ", ");
		column = column.replace("'", "\"");
		column = column.replace("[ ", "[");
		column = column.replace(" ]", "]");
		column = column.replace("[\"", "[");
		column = column.replace("\"]", "]");
		column = column.replaceAll("\"(\\d+)\"", "$1");
		column = column.substring(0,1).toLowerCase() + column.substring(1);
		return column;
	}

}

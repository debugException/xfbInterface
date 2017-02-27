package cn.tyiti.xfb.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.Map.Entry;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class DocumentUtil {
	
	/**
	 * 将参数转换成XML字符串
	 * @param loginName 登陆ID
	 * @param password  密码
	 * @param params 	消息体
	 * @throws IOException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws DocumentException 
	 * */
	public static String generateRequestParameterXML(String loginName,String password,Map<String,String> params) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, DocumentException{
		Document document = null;
		document = DocumentHelper.createDocument();	
		document.setXMLEncoding("UTF-8");
		Element root = document.addElement("ROOT");
		getHead(root, loginName, password);
		getRoot(root, params);
		return document.asXML();
	}
	
	
	/**
	 * 从document中根据指定路径获取值
	 * @param document xml文件
	 * @param path 路径
	 * */
	public static String getStrByNodePath(Document document,String path){
		if(document != null)
			return  ((Element) document.selectSingleNode(path)).getText();
		return "";
	}
	
	/**
	 * 从document中根据指定路径获取值并解密
	 * @param document xml文件
	 * @param path 路径
	 * */
	public static String getDecryptStrByNodePath(Document document,String path) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		if(document != null)
			return  decryptByPrivateKey(((Element) document.selectSingleNode(path)).getText());
		return "";
	}
	
	/**
	 * 设置头文件
	 * @throws IOException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * */
	private static void getHead(Element root,String loginName,String password) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		Element metaData = root.addElement("METADATA");
		Element userId = metaData.addElement("USERID");
		Element pwd = metaData.addElement("PASSWORD");
		userId.addText(loginName);
		pwd.addText(encryptByPrivateKey(password,getPrivateKey()));
	}
	
	/**
	 * 设置消息体
	 * @throws IOException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * */
	private static void getRoot(Element root,Map<String,String> params) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		Element batches = root.addElement("BATCHES");
		Element batche = batches.addElement("BATCH");
		for(Entry<String,String> entry : params.entrySet()){
			Element key = batche.addElement(entry.getKey());
			key.addText(encryptByPrivateKey(entry.getValue(), getPrivateKey()));
		}
	}
	
	/**
	 * 获取私钥
	 * @return String
	 */
	private static String getPrivateKey(){
		             return "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANI9Lva/AlC68AC/XT+BHbmN8JI0iVJAKyONaoZSsumghtApvPA9mDMH7ant"
				+ "LQLvTsjSvny0cAsQr6ayqfSUqTGpAzfRj+do6bj9LMFcly1XPTfXzP2yZsEZURkFEWSJJc3VFYMUa9RGmoDfGxgFdQX7qeEj387g5ejJ0IzlWi1tAgMBAAE"
				+ "CgYEAgCARcwbNXm3OvmXhakZB20eJiVDDL4EzySLx/JKBvqe6ATqujqq7Cr6WNKz6dYNqPDFTLaS5c8Tjh2/y6799rE7XEc5sFDZ8hbzO0sE5CK0bz8XU4Hd"
				+ "p1+nqy/VgAoKoV3eL45LlERuG7cwTCHcBgURCr6HKIusOKidqyTWhdYECQQD19+ooIiClynxR8Scwq2OEQZQvI+i1+jGedZ5lamSazUTpS7eQfVijGS+E0YiQ"
				+ "cowdLQBw08DTcEUR6WjfxLuVAkEA2tA6bGWu9AkVfBsR4JIrd6FIobviyLjdZY9fPZxv+smwe14lDXpQqjJRNIrOik13f8EGg1klK7tKnVXeg2Z0eQJBALNNO9ZxulbhGmspXiYuaecZXwpWJOezMMSQfRz0x83I4PkoBvI/TYPncipiDCkwN091ZKLa2e/IIbGC8r5cTVkCQBDCunbrIweWWorzF9930tSLCiE5Xxm471yEKutxPDKdmGMaxwPNRriCui2oJEH7xReIFZOiFHLRGdoLZcvy4SECQQCkJg6Zua0KP+MnW14VeKiaCoSIX6TzJkN0QJGt0nJNUG3gdz7pQPGH5is7ZsVv/5/dbKVF55mU3JwTm0+OQnkd";
	}
	
	   /**
		 * 对源数据加密并返回加密数据
		 * @param encodeText 源数据
		 * @param privateKey 私钥
		 * @return
		 * @throws InvalidKeyException
		 * @throws NoSuchAlgorithmException
		 * @throws InvalidKeySpecException
		 * @throws NoSuchPaddingException
		 * @throws IllegalBlockSizeException
		 * @throws BadPaddingException
		 * @throws IOException
		 */
		private static String encryptByPrivateKey(String encodeText,String privateKey) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
			byte[] encodeData = RSAUtil.encryptByPrivateKey(encodeText.getBytes("UTF-8"), privateKey);
			return Base64Util.byteArrayToBase64(encodeData);
		}
		
		/**
		 * 解密
		 * @throws IOException 
		 * @throws BadPaddingException 
		 * @throws IllegalBlockSizeException 
		 * @throws NoSuchPaddingException 
		 * @throws InvalidKeySpecException 
		 * @throws NoSuchAlgorithmException 
		 * @throws UnsupportedEncodingException 
		 * @throws InvalidKeyException 
		 * */
		private static String decryptByPrivateKey(String param) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
			return new String(RSAUtil.decryptByPrivateKey(Base64Util.base64ToByteArray(param), getPrivateKey()),"utf-8");
		}
}

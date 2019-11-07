package org.fh.api.util;

public class Configure {

	//商户秘钥
	private static String key = "CHYYH20181205QAZWSXPOLIKM1245789";

	//小程序ID
	private static String appID = "wxb7dd3899d065a27b";
	private static String appIdIOS = "wx8b18955b9556dc27";//ios

	//商户号
	private static String mch_id = "1503702121";
	//小程序秘钥
	private static String secret = "a0ecf5ed5d0cdc5290ae4665b810ab00";
	private static String appSecretISO = "4f7d6a0996401fb0a0bfe11e05fe185f";//ios

	public static String getSecret() {
		return secret;
	}

	public static void setSecret(String secret) {
		Configure.secret = secret;
	}

	public static String getKey() {
		return key;
	}

	public static void setKey(String key) {
		Configure.key = key;
	}

	public static String getAppID() {
		return appID;
	}

	public static void setAppID(String appID) {
		Configure.appID = appID;
	}

	public static String getMch_id() {
		return mch_id;
	}

	public static void setMch_id(String mch_id) {
		Configure.mch_id = mch_id;
	}


	public static String getAppIdIOS() {
		return appIdIOS;
	}

	public static void setAppIdIOS(String appIdIOS) {
		Configure.appIdIOS = appIdIOS;
	}

	public static String getAppSecretISO() {
		return appSecretISO;
	}

	public static void setAppSecretISO(String appSecretISO) {
		Configure.appSecretISO = appSecretISO;
	}
}

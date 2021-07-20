function encodeByXor(textStr){
	var keyStr = 'QdeiOHKDlmdaeMN<>?!@#$~&^(*)+=--678543566{}[]';
	if(textStr == null || textStr == ''){
			return '';
	}
	var retStr = '';
	for(var i=0; i<textStr.length; i++){
		var temp = textStr.charCodeAt(i);
		var salt = Math.floor(Math.random() * 1000000);
		for(var j=0; j<keyStr.length; j++){
			var key = keyStr.charCodeAt(j);
			temp = temp ^ key;
		}
		temp = temp ^ salt;
		
		if(retStr == ''){
			retStr += temp.toString(16) + '-' + salt.toString(16);//String.fromCharCode(
		}else{
			retStr += '-' + temp.toString(16) + '-' + salt.toString(16);
		}
	}
	
	return retStr;
}
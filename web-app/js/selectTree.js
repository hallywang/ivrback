//ucstar select
//id: select id
//name: select attribute name
//rootSelectname: select first option value
//sign: iterator options's prefix sign
//selectid: about the selected option id
function UcstarSelectObj(id,name,rootSelectname,sign,selectedid) {
	this.id = id;
	this.name = name;
	this.rootSelectname = rootSelectname;
	this.sign = sign;
	this.selectedid = selectedid;
	this.selectStr = "";
	this.ucstaroptions = [];
	this.ucstaroptionobjs = [];
}

//ucstar option
//id: option id
//name: option value
//pid: the node's parent id
//desc: the option's description
function UcstarOption(id,name,pid,desc) {
	this.id = id;
	this.name = name;
	this.pid = pid;
	this.desc = desc;
}

//get the options by parentid
UcstarSelectObj.prototype.getChildOptions = function(vPid) {
	var childOptions = new Array();
	//this.debug(this.ucstaroptions.length);
	for(var i = 0; i < this.ucstaroptions.length; i++) {
		if(this.ucstaroptions[i].pid == vPid) {
			//this.debug(this.ucstaroptions[i].pid + "-" + this.ucstaroptions[i].id);
			childOptions[childOptions.length] = this.ucstaroptions[i];
		}
	}
	return childOptions;
}

//debug message
UcstarSelectObj.prototype.debug = function(message) {
	alert(">>Debug:"+message);
}

//add the select's option
UcstarSelectObj.prototype.addOption = function(id,name,pid,desc) {
	//this.debug(this.ucstaroptions.length);
	this.ucstaroptions[this.ucstaroptions.length] = new UcstarOption(id,name,pid,desc);
	this.ucstaroptionobjs[id] = new UcstarOption(id,name,pid,desc);
}

//generate select string
UcstarSelectObj.prototype.genSelect = function(vPid) {
	var sPid = "-1";
	if(vPid != undefined) {
		sPid = vPid;
	}
	this.selectStr = '<select id=\'' + this.id + '\' name=\'' + this.name + '\'>';
	this.selectStr += '<option id=\'' + vPid + '\'' +
            'value=\'' + vPid + '\'>'+ this.rootSelectname + '</option>';
	this._genSelect(sPid,'');
	this.selectStr += '</select>';
	return this.selectStr;

}

//private generate select string
UcstarSelectObj.prototype._genSelect = function(vPid,vSign) {
	var oldtsign = vSign;
	var newtsign = oldtsign + this.sign;
	var childOptions = this.getChildOptions(vPid);

	if(childOptions != null && childOptions.length > 0) {
		for(var i = 0; i < childOptions.length; i++) {
			this.selectStr += '<option id=\'' + childOptions[i].id + '\'';
            this.selectStr += ' value= \'' +childOptions[i].id +'\'' ;
			if(childOptions[i].id == this.selectedid) {
				this.selectStr += ' selected';
			}
			this.selectStr += '>' + oldtsign + childOptions[i].name + '</option>';
			this._genSelect(childOptions[i].id,newtsign);
		}
	}
}

UcstarSelectObj.prototype.setSelected = function(optionid) {
	this.selectedid = optionid;
}


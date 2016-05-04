#include "ESP8266Arduino.h"

ESP8266Arduino::ESP8266Arduino(SoftwareSerial *serial){
	this->serial = serial;
	this->isDebugEnabled = false;
}

ESP8266Arduino::ESP8266Arduino(SoftwareSerial *serial, Stream *serialDebug){
	this->serial = serial;
	this->serialDebug = serialDebug;
	this->isDebugEnabled = true;
}

ESP8266Arduino::~ESP8266Arduino(){
}

bool ESP8266Arduino::testConnection(){
	this->debugMessage("Test connection");
	return true;
}

void ESP8266Arduino::debugMessage(String s){
	if(this->isDebugEnabled){
		this->serialDebug->println(s);
	}
}
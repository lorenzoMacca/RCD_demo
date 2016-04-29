#include "ESP8266Arduino.h"

ESP8266Arduino::ESP8266Arduino(Stream *serial){
	this->serial = serial;
}

ESP8266Arduino::~ESP8266Arduino(){
}

bool ESP8266Arduino::testConnection(){
	/*clearBuffer();
	write("AT");*/
	return true;
}

void ESP8266Arduino::clearBuffer() {
	while(available() > 0) {
		serial->read();
	}
}

int ESP8266Arduino::available() {
	return this->serial->available();
}

void ESP8266Arduino::write(String str) {
	this->serial->println(str);
	flush();
	delay(50);
}

void ESP8266Arduino::flush() {
	this->serial->flush();
}
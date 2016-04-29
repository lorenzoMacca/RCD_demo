#ifndef _ESP8266ARDUINO_H_
#define _ESP8266ARDUINO_H_

/*
* The following libs are needed to use:
* - Stream
*/
#if defined(ARDUINO) && ARDUINO >= 100
	#include "Arduino.h"
#else
	#include "WProgram.h"
#endif

class ESP8266Arduino{

	private:
		Stream *serial;
		void clearBuffer();
		int available();
		void write(String str);
		void flush();
	
	public:
		ESP8266Arduino(Stream *serial);
		~ESP8266Arduino();
		bool testConnection();
};

#endif
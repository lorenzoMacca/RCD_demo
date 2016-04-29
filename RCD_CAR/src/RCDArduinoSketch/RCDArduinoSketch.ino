#include "lib/ESP8266/ESP8266Arduino.h"

ESP8266Arduino *esp8266;

void setup() {
  Serial.begin(9600);
  esp8266 = new ESP8266Arduino(&Serial);
  bool isESP8266Connected = esp8266->testConnection();
}

void loop() {
  // put your main code here, to run repeatedly:

}

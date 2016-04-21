#! perl

use strict;
use warnings;
use Encode;
use IO::Socket;

print "#######################################\n";
print "#TEST SOCKET PERL CLIENT\n";
print "#######################################\n\n\n";

my $socket = new IO::Socket::INET (
                                  PeerAddr  => '127.0.0.1',
                                  PeerPort  =>  8989,
                                  Proto => 'tcp',
                               )                
or die "Couldn't connect to Server\n";

#define the test message which has to be sent to the server
my $serviceCode = "SC0\n";
my $testMessage = "####!!01ab CD23!!####\n";
#define var to catch the message
my $recv_data;

#send the service code
$socket->send(encode_utf8($serviceCode));

$recv_data = <$socket>;
chomp($recv_data);
print "$recv_data\n";

my $recv_data_test;
#send the message throught the socket
$socket->send(encode_utf8($testMessage));

$recv_data_test = <$socket>;
chomp($testMessage);
chomp($recv_data_test);

my $tmp = substr($testMessage,0,22);

if( $testMessage eq $tmp ){
	print "[OK] - test protocol 1\n";
}else{
	print "[NOK] - test protocol 1 \n";
	print "***$testMessage***\n";
	print "---$tmp---\n";
}

print "\n\n#######################################\n";
print "#END TEST SOCKET PERL CLIENT\n";
print "#######################################\n";


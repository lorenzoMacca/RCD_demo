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
my $testMessage = "####!!01ab CD23!!####\n";
my $dimMessage = 21;

#send the message throught the socket
$socket->send(encode_utf8($testMessage));

#define var to catch the message
my $recv_data;
$socket->recv($recv_data, $dimMessage);
chomp($recv_data);
chomp($testMessage);

if( $recv_data eq $testMessage ){
	print "[OK] - test protocol 1\n";
}else{
	print "[NOK] - test protocol 1 \n";
}

print "\n\n#######################################\n";
print "#END TEST SOCKET PERL CLIENT\n";
print "#######################################\n";


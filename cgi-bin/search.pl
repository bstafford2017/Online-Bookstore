#!/usr/bin/perl
use strict;
use warnings;
use CGI; 
my $query = new CGI;
my $search = $query->param('search');
my $subject = $query->param('subject');

print("Content-type: text/html\n\n");
my $compile = "/usr/bin/javac Search.java";
system($compile);
my $cmd = "";
if(!defined $search && !defined $subject){
    $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Search ";
} elsif(!defined $search){
    $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Search " . $subject;
} elsif(!defined $subject){
    $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Search " . $search;
} else {
    $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Search ". $search . " " . $subject;
}
system($cmd);
print($cmd);
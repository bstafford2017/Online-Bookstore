#!/usr/bin/perl
use strict;
use warnings;
use CGI; 
my $query = new CGI;
my $isbn = $query->param('isbn');

if(! defined $isbn){
    exit(0);
}

print("Content-type: text/html\n\n");

my $compile = "/usr/bin/javac Hyperlink.java";
system($compile);

my $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Hyperlink ";
$cmd = $cmd . $isbn;
system($cmd);
print($cmd);
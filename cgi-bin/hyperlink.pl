#!/usr/bin/perl
use strict;
use warnings;
use CGI; 
my $query = new CGI;
my $isbn = $query->param('isbn');
my $subjects = $query->param('subjects');

print("Content-type: text/html\n\n");

if(! defined $isbn){
    exit(0);
} else {
    my $compile = "/usr/bin/javac Hyperlink.java";
    system($compile);

    my $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Hyperlink ";
    $cmd = $cmd . $isbn;
}

if(! defined $subjects){
    exit(0);
} else {
    my $compile = "/usr/bin/javac Hyperlink2.java";
    system($compile);

    my $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Hyperlink2 ";
    $cmd = $cmd . $subjects;
}

system($cmd);
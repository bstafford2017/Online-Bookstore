#!/usr/bin/perl
use strict;
use warnings;
use CGI; 
my $query = new CGI;
my $search = $query->param('search');
my $subject = $query->param('subject');

if(!defined $search){
    exit(0);
}

if(!defined $subject){
    exit(0);
}

# Fix for subjects with spaces
$subject =~ s/-/ /g;

$search =~ s/^\s*(\S*)\s*$/$1/;
$search =~ s/;|>|>>|<|\*|\?|\&|\|//g;

print("Content-type: text/html\n\n");

my $compile = "/usr/bin/javac Search.java";
system($compile);

my $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Search ";
$cmd = $cmd . $search . " " . $subject;
system($cmd);
print($cmd);
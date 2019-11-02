#!/usr/bin/perl
use strict;
use warnings;
use CGI; 
my $query = new CGI;
my $title = $query->param('book-title');
my $author = $query->param('price');
my $isbn = $query->param('isbn');
my $subjects = $query->param('subjects');

if(!defined $title && !defined $author && !defined $isbn && !defined $subjects){
    exit(0);
}

$title =~ s/^\s*(\S*)\s*$/$1/;
$title =~ s/;|>|>>|<|\*|\?|\&|\|//g;
$author =~ s/^\s*(\S*)\s*$/$1/;
$author =~ s/;|>|>>|<|\*|\?|\&|\|//g;
$isbn =~ s/^\s*(\S*)\s*$/$1/;
$isbn =~ s/;|>|>>|<|\*|\?|\&|\|//g;
$subjects =~ s/^\s*(\S*)\s*$/$1/;
$subjects =~ s/;|>|>>|<|\*|\?|\&|\|//g;

my $compile = "/usr/bin/javac Create.java";
system($compile);

my $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Create ";
$cmd = $title . " " . $author . " " . $isbn . " " . $subjects;
print("Content-type: text/html\n\n");
system($cmd);
print($cmd);
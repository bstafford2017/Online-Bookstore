#!/usr/bin/perl
use CGI; 
$query = new CGI;
$title = $query->param('book-title');
$author = $query->param('price');
$isbn = $query->param('isbn');
$subjects = $query->param('subjects');

$title =~ s/^\s*(\S*)\s*$/$1/;
$title =~ s/;|>|>>|<|\*|\?|\&|\|//g;
$author =~ s/^\s*(\S*)\s*$/$1/;
$author =~ s/;|>|>>|<|\*|\?|\&|\|//g;
$isbn =~ s/^\s*(\S*)\s*$/$1/;
$isbn =~ s/;|>|>>|<|\*|\?|\&|\|//g;
$subjects =~ s/^\s*(\S*)\s*$/$1/;
$subjects =~ s/;|>|>>|<|\*|\?|\&|\|//g;

$cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Create ";
$cmd .= join $title, " ", $author, " ", $isbn, " ", $subjects;

system($cmd);
#!/usr/bin/perl
#if 0 read first command line argument, assumed to be the minutes of samples
#endif

$i = int @ARGV[0] if scalar(@ARGV) or die "usage: perl −e gencurve.pl <number of minutes>";
for ($_ = 0; $_ < $i; $_+=1){
	
	#if 0 determine number of users $n arriving in minute $_
	#endif
	$n = numUsers($_);
	#if 0 let one user arrive each 1/$n minutes
	#endif
	for $b ( 1 .. $n){
		$a = int ((($b/$n) + $_) ∗ 1000000000)∗60;
		print "\$2;$a;0;kieker.tools.slastic.tests.bookstoreDifferentRecordTypes.Bookstore.searchBook();NULL;$a$b;$a;$a;pc−vanhoorn;0;0\n"
	}
}
#if 0 workload function
#endif
sub f {
	my $x = shift @_;
	return 25∗sin(($x−13)/3.2)+10;
}
sub g {
	my $x = shift @_;
	return sqrt ($x+2)∗23;
}
sub h {
	my $x = shift @_;
	return 10 ∗ sin(($x−7)/6)+30;
}
sub numUsers {
	my $x = shift @_;
	return (1.8∗f($x) + 3.5 ∗h($x) + .8∗g($x) −110);
}

#include "function.h"

#!/usr/bin/perl
use strict;
use warnings;
use Storable;

# Setup Sounds and Descriptions
 my %sounds = %{retrieve('sounds')};
 my %desc = %{retrieve('descriptions')};


# Asking user input
print "Enter an animal's name:\n";
my $animalname = <STDIN>;
chomp($animalname);

# Print description and play sound if animal sound exists.
print "\n$desc{$animalname}" if exists $desc{$animalname};
system("play", $sounds{$animalname}) if exists $sounds{$animalname};
print "Animal sound does not exist.\n" if !exists $sounds{$animalname};
print "Animal description does not exist.\n" if !exists $desc{$animalname};

# Entering new animal info if it doesn't exist.
if (!exists $desc{$animalname})	{
	print "\nEnter a description for the new animal:\n";
	$desc{$animalname} = <STDIN>;
	print "Enter the name of the sound file for the animal's sound:\n";
	chomp(my $newsound = <STDIN>);
	$sounds{$animalname} = $newsound if -e $newsound;	
	die "New animal's sound file does not exist. Goodbye.\n" if !-e $newsound;
}

store \%sounds, 'sounds';
store \%desc, 'descriptions';

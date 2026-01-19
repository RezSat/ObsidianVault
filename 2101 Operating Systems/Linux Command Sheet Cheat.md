### Permission:

Reading (r) - 4 (highest number for highest priority)
Writing (w)  - 2 (second priority)
Execute (x) - 1 (least priority)

Why because its 3 bits operation starting from 000 (0) to 111 (7).
	4 - 100 (last bit)
	2 - 010 (middle bit)
	1 - 001 (first bit)

u - Owner
g - Group
o - Others

	chmod u [+|-] [x|r|x] filename
	chmod [first_number_for_owner][second_group][third_others] filename
		ex: chmod 777 filname (god mode)
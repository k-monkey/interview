How to run the program:

1. make sure the following files are in the same directory. some of them needs to be download due to its size:
    a. couter_ref.py (the source file)
    b. wikiprep.pl                      (the perls script needed to pre-process the wikimedia dump)
    c. 20051105_pages_articles.hgw.xml (pre-processed output from the perl script. PLEASE download it from http://www.cs.technion.ac.il/~gabr/resources/code/wikiprep/wikipedia-051105-preprocessed.tar.bz2)
    d. 20051105_pages_articles.stat.inlinks (pre-processed output from the perl script. PLEASE download it from http://www.cs.technion.ac.il/~gabr/resources/code/wikiprep/wikipedia-051105-preprocessed.tar.bz2)
    e. 20051105_pages_articles.xml      (supposed to be the original wikimedia dump. it is provided as a dummy here. 
    it can be downloaded. however, whether or not it is a dummy, does not impact the result of the python script. because
    the wikimedia dump is supposed to be pre-processed by the perl script.)
2. run command "./count_ref 20051105_pages_articles.xml"
3. look for result in "20051105_pages_articles.output" 
(NOTE: some page titles look like numbers. that is because their page title is number.)

=========================================
How it works:

The original wikimedia dump file (e.g. 20051105_pages_articles.xml needs to be pre-processed by the perl script).
the perl script was recommended by wikipedia. here is its home page: http://www.cs.technion.ac.il/~gabr/resources/code/wikiprep/

By running command "perl wikiprep.pl 20051105_pages_articles.xml"

The command will generate a bunch of pre-processed files, such as:
a. 20051105_pages_articles.hgw.xml
b. 20051105_pages_articles.stat.inlinks

essentially, the ".inlinks" file has all the reference count. the "hgw.xml" file maps all the page id to their 
page titles.

Our python program will extract the id->title mapping, then print out the title->ref_count mapping into an output file.

===============================================
What's missing:

1. the python program could have invoked the perl script from its runtime. it is not done simply because of time constrain.
2. a text encoding error could not be solved in time. it is handled in exception. and is marked by #FIXME tag. it impacts
a about 100 lines of output.
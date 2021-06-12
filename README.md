# Clean Coders Web Sandbox

### Description
This application is a sandbox for web-page design and modest development.  It includes a server that can be run 
locally currently set to port 8082.  It allows a user to customize 'toys' in the sandbox using Reagent sytax, which
can then, with the server running, be viewable in a browser.  Keep the server running and modify your
Reagent code on the go!

### Create Your Own Sandbox Toy

Note: The set up and run below will be required to actually have your toy viewable in a browser

#### Manually

    lein toy {YOUR PAGE NAME}

In main.cljs in the top portion of the :require add

    [app.sandbox.{YOUR TOY PAGE}]

Write some Reagent components in your file

Start the server

View in the browser with the uri:

    localhost:8082/sandbox/{YOUR TOY PAGE}

##### Automatically from the browser
Start the server with the directions below

Open a browser and go to

    localhost:8082/

Click the Create a Sandbox Toy button

Change the name of your file in the Sandbox

In main.cljs in the top portion of the :require add

    [app.sandbox.{YOUR TOY PAGE}]

Write some Reagent components in your file in the indicated section

Go to your page:
    
    localhost:8082/sandbox/{YOUR TOY PAGE}


### Setup
Run with Java 8 

    brew install leiningen
    brew cask install phantomjs

CSS and Javascript need to be compiled:

    # prep command will compile both
    lein prep
    # compile just the css once
    lein css once
    # compile css whenever style files are changed
    lein css
    # compile just cljs to javascript once (also runs tests)
    lein cljs once
    # compile cljs and run tests when ever a file changes
    lein cljs

For production:

    CC_ENV=production lein prep

### Running tests

    # clojure specs:
    lein spec
    # clojure specs automatically running when fileds are changed:
    lein spec -a
    
    # clojurescript specs
    brew cask install phantomjs # required to run js tests.
    lein cljs once
    # clojurescript specs automatically running when files are changed:
    lein cljs

### Development

    lein dev
    # Hosts 'lein run', 'lein clj auto', and 'lein css auto' in a single process 

This library is designed to supporting Single-Page-Application (SPA) to resolve page routing. Instances of data driven frameworks like Angular and React are designed to code with encapsulated UI components while leaving the framework to handle component interactions. But the entry point to each components still has to be hard coded at component or data level. This library solved the last meter problem by replacing all the entry point to a server-side resolver call (and ultimately auto inserted field to improve performance) and let server decide what components are linked and how interactions to them are enabled & orchestrated.

It is still in the concept phase right now and I'm just trying around to find some easier approaches to supply:
  1. smooth integration with existing server-side and client-side code
  2. some dependency-resolve is needed and I'm using Groovy's ConfigObject for the initial trail for its existing ConfigSlurper support
  3. could be a big project to grow out of control, so limiting to static dependency configuration on the server-side and unified entry point on the client-side

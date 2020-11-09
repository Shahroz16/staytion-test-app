## Overview
Because of the my limited time, I couldn't do everything I wanted but hopefully I would have managed to convey the structure and approach I adopted. 
My heavy focus was on the robust and scalable architecuture rathen than the UI, because UI can be updated as per requirements given the architecture 
is modular enough. All of the backend integration with views has been added. 

## Approach 
- I adopted `MVVM` design pattern 
- Used `Android Architecure Components`
- `Coroutines` are used for asynchronous communication with the backend
- `Flow`for asynchronous data streaming within the application
- `Okhttp WebSocket` for a non-blocking interface to a web socket
- `Dagger Hilt` for dependency injection

## Improvements 
If I had more time, I would have focused more on UI and added Unit test cases. Also, hardcoded a couple of values which should 
have been either `enums` or `constants`

## Demo video 

[sample video](demo.mov)

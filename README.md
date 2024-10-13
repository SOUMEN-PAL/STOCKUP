
# BasicStock Lookup App  
<p align="center">
[![Google Drive](https://cdn.jsdelivr.net/gh/devicons/devicon/icons/google_drive/google_drive-original.svg)](https://drive.google.com/file/d/1rUm4Y0BjYwnVjK2pgct52hlo7ysWoohl/view?usp=sharing)
</p>
  
This is a simple Android application built with Kotlin that allows users to search for a stock and view its current price, along with some basic details.  

  ![warehouse_2897818](https://github.com/user-attachments/assets/0f90c049-ddd2-4026-adbc-af269883b547)

## Features  
  
* Search for a stock by symbol (e.g., AAPL, TSLA).  
* View the current stock price, percentagechange, and company name.  
* Error handling for invalid stock symbols and network issues.  
* Loading state while fetching data.  
* Clean and simple UI using Jetpack Compose.  
  
## Setup and Run  
  
1. **Clone the repository:** 
 (https://github.com/SOUMEN-PAL/STOCKUP.git)
 2. **Open the project in Android Studio:**  
   Launch Android Studio and select "Open an existing Android Studio project." Navigate to the cloned repository directory and select it.  
  
3. **Build and run the app:**  
   * **On an emulator:**  
     - Ensure you have an Android emulator set up in Android Studio.  
     - Click the "Run" button (green play icon) in the toolbar.  
     - Select your emulator from the device list and click "OK."  
     - Ensure the device is Android 8 or any Android Version Greater
  * **On a physical device:**  
     - Enable USB debugging on your device.  
     - Connect your device to your computer via USB.  
     - Click the "Run" button and select your device from the list.
## Dependencies  
  
This project uses the following dependencies:  
  
* Jetpack Compose  
* Retrofit  
* Coil (for image loading)  
* Coroutines (for asynchronous operations)
  
## Additional Notes  
  
* The app does not require any data persistence.  
* The UI is designed to be simple and easy to use.  
* Error handling and loading states are implemented to provide a smooth user experience.
* Click on the listed items to see the prices and percentage change

## App Images and Demonstrations

### Multi Size Screen Support
![Multi Size Screen Support](https://github.com/user-attachments/assets/93477224-737f-4af0-955d-53a43f446283)

### App Screens

<div style="display: flex; justify-content: space-between;">
  <img src="https://github.com/user-attachments/assets/30758539-0051-435b-b43d-5c7447942aa6" alt="HomeScreen" width="30%">
  <img src="https://github.com/user-attachments/assets/986fa2bb-e714-4f2d-8bef-c35bda2f7856" alt="SearchStockScreen" width="30%">
  <img src="https://github.com/user-attachments/assets/29a4d61b-2528-4871-897e-3ebdd629fdb5" alt="SearchedStock" width="30%">
</div>

<div style="display: flex; justify-content: space-between;">
  <img src="https://github.com/user-attachments/assets/a8899e16-c58a-461c-86b8-5e74216ee1fb" alt="InvalidSymbol" width="30%">
  <img src="https://github.com/user-attachments/assets/df89533c-8e8c-4185-b243-0dc1cf2a31b1" alt="NetworkError" width="30%">
</div>

### Network Error Demonstration
![Network Error Demo](https://github.com/user-attachments/assets/345fcc6e-1403-4a25-9e29-0f234e20aa9b)


## Contributing  
  
Contributions are welcome! Please feel free to submit pull requests.

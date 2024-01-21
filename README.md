This is a simple library that helps wrap api response and retrieve the data in functional style.

### Setup
#### Add this in your root `build.gradle` file:

```groovy
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

### Dependency
Add the following to your `build.gradle` dependencies:

```groovy
dependencies {
    implementation("com.github.Sam-muigai:ApiResult:<version>")
}
```

## Usage
In your repository you can wrap the data your are expecting from an apiCall in ApiResult

```kotlin
fun getData(): Flow<ApiResult<String>> = flow {
        try {
            val data = "Sample Data"
            emit(ApiResult.Success(data))
        } catch (throwable: Throwable) {
            emit(ApiResult.Error(throwable = throwable))
        }
    }
```
If you are not doing complex task then you can use handleApiCall function as in the example below.

```kotlin
suspend fun getData(): Flow<ApiResult<String>> = flowOf(
        handleApiCall {
            val sampleData = "Sample data"
            sampleData
        },
    )
```

In the viewModel you can retrieve the data in a functional style as shown

```kotlin
fun getData() {
        viewModelScope.launch {
            repository.getData().collectLatest { apiResult ->
                apiResult.onSuccess { data ->
                    println(data)
                }.onError { message, throwable ->
                    println(throwable.localizedMessage)
                }
            }
        }
    }
```

TOPIC: MOVIE

CLASS USER:
This Java code defines a User class in the models package. The class is used to represent a user with several attributes, and it includes constructors, getter and setter methods, as well as a custom toString() method for displaying user information.
Attributes:
user_id: Integer, a unique identifier for the user.
user_name: String, the user's name.
user_age: Integer, the user's age.
user_gender: Boolean, represents the user's gender (true for male, false for female).
preferred_genre: String, represents the user's preferred genre.
password: String, the user's password.
Constructors:
No-argument constructor: Initializes a User object with default values.
Constructor with parameters (excluding user_id): Initializes a User object with specific user_name, user_age, user_gender, preferred_genre, and password.
Constructor with all fields: Initializes a User object, including the user_id along with the other parameters.
Getters and Setters:
These methods allow you to access and modify the values of the user’s attributes. For example, getUser_name() retrieves the user's name, while setUser_name(String user_name) sets the value of the user's name.
Private Method gender_casting():
Converts the boolean user_gender to a string, either "Male" or "Female".
Override of toString() Method:
Provides a string representation of the User object, which includes user_id, user_name, user_age, gender (converted from boolean), and preferred_genre.


CLASS MOVIE:
This Java code defines a Movie class within the models package, which is used to represent a movie and its associated details. It includes attributes for the movie's properties, constructors, getter and setter methods, and an overridden toString() method for displaying the movie information.
Attributes:
movie_id: Integer, a unique identifier for the movie.
movie_name: String, the name of the movie.
movie_genre: String, the genre of the movie (e.g., Action, Drama).
age_restriction: Integer, the minimum age required to watch the movie (e.g., 18 for restricted content).
rating: Double, the movie's rating (e.g., a number between 0 and 10).
reviews: String, the reviews or a description of the movie.
Constructors:
Constructor with parameters (excluding movie_id): Initializes a Movie object with movie_name, movie_genre, age_restriction, rating, and reviews.
Constructor with all fields: Initializes a Movie object, including the movie_id along with the other parameters.
Getters and Setters:
These methods allow you to access and modify the values of the movie's attributes. For example, getMovie_name() retrieves the movie's name, while setMovie_name(String movie_name) sets the value of the movie's name.
Override of toString() Method:
Provides a string representation of the Movie object, including the movie_id, movie_name, movie_genre, age_restriction, and rating. This method is useful for displaying a brief summary of the movie.
Purpose:
The Movie class encapsulates all the necessary details about a movie and provides methods for managing these details, along with a custom method for displaying the information. It can be used to store and manipulate movie-related data in a movie-related application.


CLASS GENRE:
The Genre class represents a genre entity with two attributes: genre_id and genre_name. It provides constructors for creating genre objects, getter and setter methods for accessing and modifying its fields, and an overridden toString() method for a readable string representation.
Attributes:
genre_id: Unique identifier for the genre.
genre_name: Name of the genre.
Constructors:
Default constructor (Genre()) initializes an empty object.
Constructor with genre_name parameter initializes an object with a genre name.
Constructor with genre_id and genre_name sets both attributes.
Methods:
Getter and setter methods for genre_id and genre_name.
toString() method returns a formatted string representation of the Genre object.


CLASS REVIEW:
The Review class represents a user review for a movie in a system. It contains attributes to store details about the review, including:
Attributes:
id: A unique identifier for the review.
movieId: The identifier of the movie being reviewed.
userId: The identifier of the user who wrote the review.
reviewText: The actual text content of the review.
Constructors:
A default constructor with no parameters.
A constructor that takes movieId, userId, and review Text.
A constructor that also includes an id, using constructor chaining.
Methods:
Getters and setters for each field, allowing access and modification of the attributes.
toString method, which returns a string representation of the Review object.

CLASS CurrentUser:
The Current User class is a singleton utility class designed to store and manage the currently logged-in user within an application.
Static Field current User: Holds the reference to the logged-in user.
Private Constructor: Prevents instantiation of the class, ensuring that it can only be accessed via static methods.
setCurrentUser(User user):
Sets the current user.
Logs a message indicating whether the user was successfully saved or not.
getCurrentUser(): Returns the currently logged-in user.
isLogged(): Returns true if a user is logged in, otherwise false.
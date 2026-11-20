# Art Gallery System

CS4108 Advanced Programming – Group Project
Java · JavaFX + FXML · MVC · Desktop application

> **Status:** project skeleton only. Every file contains comments that explain what to implement. There is no working code yet, so the project will not compile or run at this stage.

---

## 1. Project idea

The Art Gallery System is a desktop application for browsing a small art gallery. Users can:

- browse artworks in a visual gallery,
- filter by category (**Oil Paintings** or **Pastel Paintings**) and search by title or artist,
- open an artwork to see its image, title, artist, description and category,
- **like**, **rate (1–5 stars)** and **comment on** artworks,
- see their own activity, plus simple gallery statistics.

All artworks are added **manually by the developer** (by editing `data/artworks.txt` and copying the image into the images folder). Regular users **cannot** upload, edit or delete artworks; they can only interact with the existing ones. All user interactions are saved to text files so they are still there after the application restarts.

Main entities: **Artwork**, **User**, **Comment**, **Like**, **Rating** (plus the **ArtworkCategory** enum).

---

## 2. Folder structure

```
ArtGallerySystem/
├── pom.xml                         Maven build file (comments only)
├── README.md                       This file
├── data/                           Local text files (R4) – start EMPTY
│   ├── artworks.txt
│   ├── users.txt
│   ├── comments.txt
│   ├── likes.txt
│   └── ratings.txt
└── src/main/
    ├── java/
    │   ├── module-info.java
    │   └── com/artgallery/
    │       ├── Main.java                       App entry point
    │       ├── model/                          M – data only (POJOs)
    │       │   ├── Artwork.java
    │       │   ├── ArtworkCategory.java
    │       │   ├── User.java
    │       │   ├── Comment.java
    │       │   ├── Like.java
    │       │   └── Rating.java
    │       ├── controller/                     C – one per FXML screen
    │       │   ├── WelcomeController.java
    │       │   ├── LoginController.java
    │       │   ├── RegisterController.java
    │       │   ├── GalleryController.java
    │       │   ├── ArtworkDetailsController.java
    │       │   ├── MyActivityController.java
    │       │   └── StatisticsController.java
    │       ├── service/                        Business logic + Streams
    │       │   ├── ArtworkService.java
    │       │   ├── UserService.java
    │       │   ├── CommentService.java
    │       │   ├── LikeService.java
    │       │   ├── RatingService.java
    │       │   └── StatisticsService.java
    │       ├── repository/                     Read/write the data files
    │       │   ├── FileManager.java
    │       │   ├── ArtworkRepository.java
    │       │   ├── UserRepository.java
    │       │   ├── CommentRepository.java
    │       │   ├── LikeRepository.java
    │       │   └── RatingRepository.java
    │       └── util/                           Shared helpers
    │           ├── InputValidator.java
    │           ├── SceneNavigator.java
    │           ├── AlertHelper.java
    │           ├── UserSession.java
    │           └── ImageHelper.java
    └── resources/com/artgallery/
        ├── view/                               V – the 7 FXML screens
        │   ├── WelcomeView.fxml
        │   ├── LoginView.fxml
        │   ├── RegisterView.fxml
        │   ├── GalleryView.fxml
        │   ├── ArtworkDetailsView.fxml
        │   ├── MyActivityView.fxml
        │   └── StatisticsView.fxml
        ├── css/
        │   └── style.css
        └── images/
            ├── oil/        ← put Oil Painting images here
            ├── pastel/     ← put Pastel Painting images here
            └── app/        ← logo / "no image" picture (optional)
```

(The `.gitkeep` files inside the image folders only keep the empty folders in Git/zip. Delete them after you add images.)

---

## 3. Responsibility of each package

| Package | Responsibility | May use JavaFX? |
|---|---|---|
| `model` | Plain data classes (POJOs): attributes, constructor, getters/setters, `toString()`. **Data only** ("Models data, nothing else!", Lecture 9). | **No** |
| `view` (resources) | FXML files built in Scene Builder. They only describe what the screen looks like. | – (FXML) |
| `controller` | Handles user events, validates input, calls services, updates the view. Each FXML view is paired with one controller. | Yes |
| `service` | Business logic: rules (one like per user, rating 1–5…), searching, filtering, sorting and statistics with Streams. | **No** |
| `repository` | Reads and writes the text files in `data/`. Converts lines ⇄ objects. | **No** |
| `util` | Helpers shared by controllers: validation, navigation, alerts, the logged-in user, image loading. | Some (navigation, alerts, images) |

---

## 4. Purpose of every file

### Root
| File | Purpose |
|---|---|
| `pom.xml` | Maven settings: project info, JavaFX dependencies (`javafx-controls`, `javafx-fxml`), run plugin. |
| `module-info.java` | Declares the JavaFX modules the app needs and opens the controller package to FXML. |
| `Main.java` | Starts JavaFX, loads all data from files, gives the Stage to `SceneNavigator`, shows `WelcomeView.fxml`. |

### model
| File | Purpose |
|---|---|
| `Artwork.java` | One artwork: id, title, artist, description, category, imagePath, year. |
| `ArtworkCategory.java` | Enum with exactly two values: `OIL`, `PASTEL`. |
| `User.java` | One user: username, password, fullName, email. |
| `Comment.java` | One comment: id, username, artworkId, text, date. |
| `Like.java` | One like: username, artworkId. |
| `Rating.java` | One rating: username, artworkId, stars (1–5). |

### controller
| File | Purpose |
|---|---|
| `WelcomeController.java` | Buttons to Login, Register, Exit. |
| `LoginController.java` | Checks the username and password, uses Enter to submit, disables the button while fields are empty, saves the user in session. |
| `RegisterController.java` | Validates the new account, prevents duplicate usernames, saves to `users.txt`. |
| `GalleryController.java` | Shows the artwork cards, runs a live search, filters with RadioButtons, sorts with a ComboBox, opens details on click, and navigates to the other screens. |
| `ArtworkDetailsController.java` | Shows one artwork. Handles like/unlike, the rating Slider and comments (ListView). |
| `MyActivityController.java` | Lists the user's comments, likes and ratings. Double-click opens an artwork. |
| `StatisticsController.java` | Displays the statistics report from `StatisticsService`. |

### service
| File | Purpose |
|---|---|
| `ArtworkService.java` | Gets, finds, searches (Predicate), filters by category and sorts artworks. |
| `UserService.java` | Register (no duplicates) and login. |
| `CommentService.java` | Adds comments and gets comments by artwork or by user. |
| `LikeService.java` | Toggles like/unlike, counts likes and gets the user's liked artworks. |
| `RatingService.java` | Rates or updates a rating, calculates the average and gets the user's ratings. |
| `StatisticsService.java` | Builds the report with `Collectors.groupingBy` and `counting`, plus the top-liked and top-rated lists. |

### repository
| File | Purpose |
|---|---|
| `FileManager.java` | Shared `readLines` / `writeLines` for files in `data/`, with try/catch. |
| `ArtworkRepository.java` | **Loads only** `artworks.txt` (the app never changes artworks). |
| `UserRepository.java` | Load/save `users.txt`. |
| `CommentRepository.java` | Load/save `comments.txt`. |
| `LikeRepository.java` | Load/save `likes.txt`. |
| `RatingRepository.java` | Load/save `ratings.txt`. |

### util
| File | Purpose |
|---|---|
| `InputValidator.java` | Checks for empty fields, username rules, email format, password length and comment length, and blocks the `|` character. Some checks are written as `Predicate`. |
| `SceneNavigator.java` | Loads an FXML file and shows it in the main Stage (with the CSS). |
| `AlertHelper.java` | Shows Error, Success, Warning and Confirmation Alert dialogs. |
| `UserSession.java` | Remembers the logged-in user and the selected artwork between screens. |
| `ImageHelper.java` | Loads an artwork image safely. If the file is missing, it uses a default image instead of crashing. |

### resources
| File/Folder | Purpose |
|---|---|
| `view/*.fxml` | The 7 screens. Each file describes the layout panes, controls (with `fx:id`), interactions and controller. |
| `css/style.css` | One shared stylesheet: colors, fonts, buttons, error/success labels, artwork cards. |
| `images/oil/`, `images/pastel/`, `images/app/` | Image folders (empty – add your own images). |

---

## 5. How the two categories are organized

The categories are organized in **three matching places**, so everything stays consistent:

1. **Code:** the enum `ArtworkCategory` has exactly `OIL` and `PASTEL`.
2. **Data:** each line in `artworks.txt` has a category field with the text `OIL` or `PASTEL`.
3. **Images:** oil images go in `images/oil/` and pastel images go in `images/pastel/`.

The Gallery screen filters between *All / Oil Paintings / Pastel Paintings* using RadioButtons in one ToggleGroup. The Statistics screen counts the artworks in each category with `Collectors.groupingBy`.

---

## 6. Where the developer adds artwork images

To add a new artwork (developer only):

1. Copy the image file (`.jpg` or `.png`) into
   `src/main/resources/com/artgallery/images/oil/` **or** `.../images/pastel/`.
2. Add one line to `data/artworks.txt` (format in section 7), with `imagePath` set to `oil/<file name>` or `pastel/<file name>`.
3. Restart the application.

Tips: use simple file names (no spaces, lowercase), and keep the images at a similar size, for example about 800 px wide.

---

## 7. Data files (R4)

All files are in `data/`. They are **empty now** on purpose, so no fake users or artworks are included.
Format: **one record per line**, fields separated by `|`. The `|` character is not allowed inside user input (`InputValidator` checks this).

| File | Fields (in order) | Written by |
|---|---|---|
| `artworks.txt` | `id \| title \| artist \| description \| category \| imagePath \| year` | **Developer only** (by hand) |
| `users.txt` | `username \| password \| fullName \| email` | App, on registration |
| `comments.txt` | `commentId \| username \| artworkId \| date \| text` | App, when a comment is added |
| `likes.txt` | `username \| artworkId` | App, on like/unlike |
| `ratings.txt` | `username \| artworkId \| stars` | App, on rate/re-rate |

**Field notes**
- `id` (artwork): unique short text, e.g. `A1`.
- `category`: `OIL` or `PASTEL` only.
- `imagePath`: relative to the `images/` folder, e.g. `oil/<file>.jpg`.
- `username`: unique. It links a user to their comments, likes and ratings.
- `commentId`: generated by `CommentService` (e.g. `C1`, `C2`, …).
- `date`: `yyyy-mm-dd`.
- `stars`: whole number from 1 to 5.

**Relationships**
```
User 1 ─── * Comment * ─── 1 Artwork
User 1 ─── * Like    * ─── 1 Artwork     (max one Like per user per artwork)
User 1 ─── * Rating  * ─── 1 Artwork     (max one Rating per user per artwork)
Artwork * ─── 1 ArtworkCategory (OIL | PASTEL)
```
The links are stored as ids (`username`, `artworkId`) inside Comment, Like and Rating. Objects are not stored inside other objects, which keeps the model and the files simple.

**When data is loaded and saved**
- **Load:** once, in `Main.start()`. Each service asks its repository to read its file.
- **Save:** right after every change (register, comment, like/unlike, rate). The service calls `repository.saveAll(list)`.

---

## 8. How the components communicate (MVC)

```
 ┌────────────┐  user action   ┌──────────────┐   calls    ┌────────────┐   reads/writes   ┌────────────┐
 │  VIEW      │ ─────────────► │ CONTROLLER   │ ─────────► │  SERVICE   │ ───────────────► │ REPOSITORY │ ──► data/*.txt
 │ (FXML)     │                │ (events,     │            │ (rules,    │                  │ (files)    │
 │            │ ◄───────────── │  validation) │ ◄───────── │  Streams)  │ ◄─────────────── │            │
 └────────────┘  update labels └──────────────┘  model     └────────────┘  model objects   └────────────┘
                 / lists                         objects
                                 MODEL (Artwork, User, Comment, Like, Rating) is passed between all layers
```

Example: **a user adds a comment**
1. **View:** the user types in `commentArea` and clicks *Add Comment*.
2. **Controller** (`ArtworkDetailsController`): the action event runs, and the controller validates the text with `InputValidator`.
3. **Service** (`CommentService.addComment`): creates a `Comment` **model** object and adds it to the list.
4. **Repository** (`CommentRepository.saveAll`) writes `comments.txt` using `FileManager`.
5. **Controller** refreshes `commentsListView` and shows "Comment added successfully".

Rules: model classes never import JavaFX, controllers never touch files directly, and the FXML files contain no logic.

---

## 9. Where each university requirement is implemented

| Req. | What is required | Where in this project |
|---|---|---|
| **R1 – JavaFX GUI** | ≥ 5 FXML windows, layout panes, varied controls, ≥ 1 ListView/TableView, images, CSS | **7** FXML files in `view/`. Panes: BorderPane, VBox, HBox, GridPane, FlowPane, ScrollPane. Controls: Label, TextField, PasswordField, Button, CheckBox (Register), RadioButton (Gallery), ComboBox (Gallery sort), TextArea, Slider, ImageView, Hyperlink. **ListView** in ArtworkDetails, MyActivity and Statistics. `css/style.css`. `images/` folders. |
| **R2 – Events** | Action events, mouse/keyboard events, binding/ChangeListener, navigation | **Action:** all buttons. **Keyboard:** Enter to log in (`LoginController`). **Mouse:** click a card (`GalleryController`), double-click a liked artwork (`MyActivityController`). **Binding/Listener:** button disabled while fields are empty (Login), CheckBox → Register button, live search (Gallery), Slider → label and comment counter (ArtworkDetails). **Navigation:** `SceneNavigator.switchTo(...)` from event handlers. |
| **R3 – MVC** | Separate model / view / controller, GUI-independent model | `model/` (POJOs, no JavaFX), `resources/.../view/` (FXML), `controller/` (one per view), plus `service/` and `repository/` to keep the logic out of controllers. |
| **R4 – Persistence** | Local files, load on start, save on change, separate files | `data/artworks.txt`, `users.txt`, `comments.txt`, `likes.txt`, `ratings.txt`. Read and written by `repository/*` through `FileManager`. Loaded in `Main.start()`, and saved by the services after every change. |
| **R5 – Functional** | Lambdas / method references, built-in functional interface, Streams (filter, map, sorted) + Collectors report | **Lambdas** for all event handlers and listeners. **Method references** such as `Artwork::getTitle` and `Rating::getStars`. **Predicate** in `ArtworkService.search` and `InputValidator`. **filter:** search, category, comments by artwork. **map:** liked artworks, display strings. **sorted:** title, most-liked, top-rated. **Collectors:** `groupingBy(..., counting())` in `StatisticsService`. |
| **R6 – Validation & errors** | Validate input, clear messages, no crashes | `InputValidator` checks empty fields, formats, lengths, duplicate usernames, the rating range and the `\|` character. `AlertHelper` and red/green message labels show results. try/catch blocks in `FileManager`, the repositories (broken lines), `SceneNavigator` (FXML loading) and `ImageHelper` (missing images). |

Use this table later as the starting point for the **requirements table** in the report (add the real method names after you implement them).

---

## 10. Suggested division of work

Each member owns clear files (the project rules require it), but **everyone must understand the whole system** for the discussion.

### Team of 5
| Member | Owns | Main requirements |
|---|---|---|
| **1 – Team leader / Models & Persistence** | all `model/*`, `repository/*`, `Main.java`, `module-info.java`, `pom.xml` | R3, R4 |
| **2 – Accounts** | `WelcomeView`, `LoginView`, `RegisterView` + their controllers, `UserService`, `InputValidator` | R1, R2 (keyboard, binding), R6 |
| **3 – Gallery** | `GalleryView` + `GalleryController`, `ArtworkService`, `ImageHelper`, `style.css` | R1, R2 (mouse, listener), R5 (filter/sort/Predicate) |
| **4 – Artwork interaction** | `ArtworkDetailsView` + controller, `CommentService`, `LikeService`, `RatingService` | R1 (ListView, Slider), R2, R6 |
| **5 – Activity & Statistics** | `MyActivityView`, `StatisticsView` + controllers, `StatisticsService`, `SceneNavigator`, `AlertHelper`, `UserSession` | R5 (map, Collectors), R2 (navigation) |

### Team of 4
Merge **Member 5's** work into the others:
- `MyActivity` screen → Member 4
- `StatisticsService` / `StatisticsView` → Member 3
- `SceneNavigator`, `AlertHelper`, `UserSession` → Member 1

### Suggested order
1. Member 1 finishes the **model classes first**, because everyone depends on them.
2. Agree on all `fx:id` names and the file formats in section 7.
3. Build the screens in Scene Builder, connect the controllers, then the services.
4. Add the artwork images and `artworks.txt` lines, then test everything together.
5. Prepare the report: class diagram, screenshots, the R1–R6 table and the contribution table.

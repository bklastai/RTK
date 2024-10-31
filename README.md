# Edit right before submission:
I typed up the below README in the planning phase but I ran out of time during development, so I 
didn't complete everything that is mentioned below. I didn't delete that old content though and am leaving 
it to show off my product thinking process. In terms of actually completed 
features, I was only able to display the transactions in a chronological order, grouped by day and 
added some navigation shortcuts between calendar days.

### In a nutshell
- I tried to be focused on the prompt, namely wearing my product hat and coming up with useful 
features for the *Ramp cardholder*. See [User definition](#user-definition) section for more details
on that.
- Code quality was largely deprioritized, please see [Todos](#todos) for the ways I would have 
improved the code quality if I had spent more time on the project.
- The prompt implied that it was allowed to either add new libraries or use existing ones, while
  *replacing* existing libs was not mentioned. After looking at the code, decided there wasn't
  anything I drastically disagreed with anyway except maybe the use Gson, which I would have 
  replaced with kotlinx serialization that I prefer due to support of kotlin specific features, 
  which reduces code verbosity/platform gotchas, and - more importantly - its performance is better
  because it doesn't rely on reflection. The stricter style of its usage is also more to my taste.
  Regardless, this falls into the category of "code quality", so I deprioritized it. Navigation
  library upgrade to `2.8.x`, which would have given type safe routes, also got deprioritized on the
  same basis.

## Use cases, clarification..
Assuming the [definition](#user-definition) of Ramp cardholder, and the provided example use cases,
my interpretation of use cases boils down to two categories:
  - User-friendly view of/quick search & navigation tools of recent transactions to find one 
  particular transaction whose accuracy/existence the user wants to check (e.g. against a physical
  receipt).
  - User-friendly view of the user's financial state + tools to manage it, i.e. view spending
  patterns, unexpected transactions, cash flows, etc. and be able to set up notifications or even 
  enforceable spending limits on your cards.

## Features
Per use case:
**Find a transaction**
- There are 4 cards in the provided data, so I added the `CardsFragment` as the default
destination of the app, so user can navigate to the given transaction easier *when they know the
card* that was used for it. That said, I anticipate the `AllCardsFragment` would be the most
used destination by the *Ramp cardholder*, so I made navigation to that the
first/easiest-to-access element in this new default fragment.
- I implemented and used `DaySpend` as the main model for the list of transactions instead of
`Transaction`. This makes it easier to find the receipt *by simple scrolling* since they are
listed chronologically and grouped by day.
- Added sticky header that shows the month and day of the top-most visible `Transaction`. Added
buttons to navigate to the previous or next day/month. This allows for faster *navigation to*
of the receipt the user is looking for w/t excessive scrolling.
- Added filters for searching by exact date and merchant name
- Used a top app bar for consistency with Material design guidelines (i.e. makes it easier for
new users to acclimate to the overall UI/navigation of the app, which is part of the original
task of finding the given receipt)
**Analyse financial state**
- I implemented detail views for spending patterns in a day/month/all-time, per card and per
  user (Ramp cardholder) account. There, I displayed derived analytics of the user's spending
  patterns in the form of:
  - Spending per category of goods & services (see `PieChartState`).
  - Spending on local merchants vs out-of-state.

## Todos
- There should be persistence, likely in the form SQLite/Room. This would change the implementation
of practically every feature in the app.
- User sign-in and profile management skipped since those are more like essential utilities than
features. User data is replaced with dummy data to enable features (e.g. default arg, 
`userResidenceState`, in the function `DaySpend#localSpendRatio`).
- Standalone strings/dimensions used across compose code should become resources

## User definition
- Since Ramp gives out credit cards to businesses, my assumption is there is one business account
that represents the "Ramp cardholder". It has multiple cards issued to it.
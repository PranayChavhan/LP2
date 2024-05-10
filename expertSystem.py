# Sample data structure for flight and cargo schedules
search_tree = {
    "hello": "Hello!",
    "flights": {
        "domestic": {
            "flight1": {
                "onboarding": "NYC",
                "destination": "LAX",
                "class": "economy",
                "time": "2024-05-10 08:00",
                "duration": "6h",
                "price": "$300",
            },
            "flight2": {
                "onboarding": "NYC",
                "destination": "MIA",
                "class": "business",
                "time": "2024-05-10 10:00",
                "duration": "3h",
                "price": "$400",
            },
            # Add more flight data as needed
        },
        "international": {
            "flight1": {
                "onboarding": "NYC",
                "destination": "LHR",
                "class": "first",
                "time": "2024-05-10 18:00",
                "duration": "7h",
                "price": "$1000",
            },
            # Add more flight data as needed
        },
    },
    "cargo": {
        # Add cargo schedule data here
        "cargo1": {
            "onboarding": "NYC",
            "destination": "LAX",
            "date": "2024-05-10",
            "time": "09:00",
            "type": "general",
            "weight_limit": "2000kg",
            "price_per_kg": "$1.5",
        },
        # Add more cargo data as needed
    },
}


def ask_questions():
    print("Let's help you find the best flights or cargo schedules.")
    onboarding = input("Where will you be departing from? ")
    destination = input("Where is your destination? ")
    class_type = input("What class would you prefer (e.g., economy, business, first)? ")
    date = input("When would you like to travel (e.g., 2024-05-10)? ")

    search_preferences(onboarding, destination, class_type, date)

def search_preferences(onboarding, destination, class_type, date):
    print("Searching for flights...")
    flights = search_tree.get('flights', {})
    found_flights = []

    for category, flights_dict in flights.items():
        for flight, details in flights_dict.items():
            if (details.get('onboarding') == onboarding and
                details.get('destination') == destination and
                details.get('class') == class_type and
                details.get('time').startswith(date)):
                found_flights.append((flight, details))

    if found_flights:
        print("Here are the available flights based on your preferences:")
        for flight, details in found_flights:
            print(f"Flight: {flight}")
            print(f"Onboarding: {details['onboarding']}")
            print(f"Destination: {details['destination']}")
            print(f"Class: {details['class']}")
            print(f"Time: {details['time']}")
            print(f"Duration: {details['duration']}")
            print(f"Price: {details['price']}\n")
    else:
        print("No flights found matching your preferences.")




def search(query: str, subtree):
    if type(subtree) == str:
        print(f"Reply: {subtree}")
        return
    children = list(subtree.keys())
    for child in children:
        if child in query:
            search(query, subtree[child])
            break
    else:
        print("Could not understand context, available options are: ")
        print(children)



while True:
    question = input("Enter your query or type 'find' to find the best flights or cargo schedules: ")
    if question.lower() == "find":
        ask_questions()
    else:
        search(question, search_tree)

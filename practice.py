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

def ask_question():
    print("Lests help you to find the flights.")
    onboarding = input("Where will you be departed from? ")
    destination = input("Where you want to go")
    class_type = input("Enter class ")
    date = input("Enter date")
    
    search_preferences(onboarding, destination, class_type, date)
    

def search_preferance(onboarding, destination, class_type, date):
    print("Searching for flights.....");
    flights = search_tree.get('flights', {})
    fount_flight = [];
    
    for flight_dict in flights.items():
        for flight, details in flight_dict.items():
            if(
                details.get('onboarding') == onboarding and
                details.get('destination') == destination and 
                details.get('class') == class_type and 
                details.get('time').startswith(date)
            ):
                fount_flight.append((flight, details))
    
    if fount_flight:
        print("List of flight")
        for flight, details in found_flights:
            print(f"flight : {flight}")
            print(f"onboarding : {details['onboarding']}")
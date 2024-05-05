import re
import random
context = {
    'favorite_color': None,
}

R_EATING = "I don't like eating anything because I'm a bot obviously!"
R_ADVICE = "If I were you, I would go to the internet and type exactly what you wrote there!"

def unknown():
    response = ["Could you please re-phrase that? ",
                "...",
                "Sounds about right.",
                "What does that mean?"][
        random.randrange(4)]
    return response

def message_probability(user_message, recognised_words, single_response=False, required_words=[]):
    message_certainty = 0
    has_required_words = True
    
    for word in user_message:
        if word in recognised_words:
            message_certainty += 1

    percentage = float(message_certainty) / float(len(recognised_words))

    for word in required_words:
        if word not in user_message:
            has_required_words = False
            break

    if has_required_words or single_response:
        return int(percentage * 100)
    else:
        return 0

def check_all_messages(message):
    highest_prob_list = {}

    def response(bot_response, list_of_words, single_response=False, required_words=[]):
        nonlocal highest_prob_list
        highest_prob_list[bot_response] = message_probability(message, list_of_words, single_response, required_words)

    # Responses -------------------------------------------------------------------------------------------------------
    response('Hello!', ['hello', 'hi', 'hey', 'sup', 'heyo'], single_response=True)
    response('See you!', ['bye', 'goodbye'], single_response=True)
    
    if 'favorite' in message and 'color' in message and 'what' not in message:
        response('What is your favorite color?', ['favorite', 'color'], single_response=True)

    if 'my favorite color is' in ' '.join(message):
        favorite_color = message[-1]
        context['favorite_color'] = favorite_color
        return f"Got it! Your favorite color is {favorite_color}."

    if 'what is my favorite color' in ' '.join(message):
        favorite_color = context.get('favorite_color')
        if favorite_color:
            return f"Your favorite color is {favorite_color}."
        else:
            return "You haven't told me your favorite color yet."

    best_match = max(highest_prob_list, key=highest_prob_list.get)
    return best_match if highest_prob_list[best_match] > 0 else "Sorry, I didn't understand that."

def get_response(user_input):
    split_message = re.split(r'\s+|[,;?!.-]\s*', user_input.lower())

    response = check_all_messages(split_message)

    return response

while True:
    user_input = input('You: ')
    
    bot_response = get_response(user_input)
    
    print('Bot:', bot_response)
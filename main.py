# website_trial is a python package because of the __init__.py
from website1 import create_app

app = create_app()
# app.config['SERVER_NAME'] = ''

# run web server only when you want it
if __name__ == '__main__':
    # run application. Any changes in python code will rerun the web server
    
    app.run(debug=True)
    # turn off (false) during production
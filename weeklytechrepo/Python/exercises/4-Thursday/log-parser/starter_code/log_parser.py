import json
import re

def main():
    raw_log_file = """
192.168.1.10 - - [10/Oct/2026:13:55:36] "GET /home.html HTTP/1.1" 200
203.0.113.50 - - [10/Oct/2026:13:56:01] "GET /admin.php HTTP/1.1" 404
10.0.0.5 - - [10/Oct/2026:13:57:12] "POST /login HTTP/1.1" 200
198.51.100.22 - - [10/Oct/2026:13:58:45] "GET /secret_config.txt HTTP/1.1" 404
172.16.0.42 - - [10/Oct/2026:13:59:10] "GET /images/logo.png HTTP/1.1" 200
192.0.2.144 - - [10/Oct/2026:14:00:22] "GET /dashboard.php HTTP/1.1" 404
"""

    flagged_ips = []

    # 1. Iterate through strings line-by-line using .splitlines()
    for line in raw_log_file.strip().splitlines():
        pass
        # 2. Extract only lines containing "404"
        # TODO: Write an `if` statement to check if the string "404" is in the line
        
        # 3. Use RegEx to find the IP address
        # Hint: The pattern for an IP could be r'\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}'
        # TODO: Execute re.search() or re.findall()
        
        # 4. Append the match to the flagged_ips List
        # TODO: Append the extracted string

    # 5. Serialization!
    # TODO: Create a dictionary containing {"flagged_ips": flagged_ips}
    
    # 6. JSON output
    # TODO: Use json.dumps(dictionary, indent=4) and print it out
    

if __name__ == "__main__":
    main()

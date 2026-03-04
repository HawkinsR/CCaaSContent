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

    # 1. Iterate line by line
    for line in raw_log_file.strip().splitlines():
        # 2. Check for 404
        if "404" in line:
            # 3. RegEx extraction. 
            # Pattern literally translates to: "digit(1 to 3 times) dot digit(1 to 3 times)..."
            ip_pattern = r'\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}'
            
            # search() returns a Match object, from which we extract the string using .group()
            match = re.search(ip_pattern, line)
            
            if match:
                # 4. Append the String
                flagged_ips.append(match.group())

    # 5. The Dictionary Structure
    security_report = {
        "flagged_ips": flagged_ips
    }
    
    # 6. Serialize to a JSON String
    final_output = json.dumps(security_report, indent=4)
    print("Security Report Generated:")
    print(final_output)

if __name__ == "__main__":
    main()

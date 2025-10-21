# Fergal Craker - FergalVer. Kahoot Automation Tool

A Java-based automation tool designed to assist with answering educational quiz questions in FergalVer. Kahoot sessions.

## Overview

This tool uses AI-powered image recognition and natural language processing to automatically answer questions presented in educational quiz formats. It connects to AI services to analyze question images and provide appropriate answers.

## Features

- Automated question detection and answering
- AI-powered image recognition for quiz questions
- Configurable AI model selection
- Multi-threaded processing for concurrent sessions
- Support for custom AI API endpoints

## Setup

1. Clone this repository
2. Ensure you have Java and Maven installed
3. Configure your API settings in `config.json` (created automatically on first run)
4. Run the application

## Configuration

On first run, the tool automatically creates a `config.json` file with default settings:
- **base_url**: https://api.siliconflow.cn/v1 (SiliconFlow API endpoint)
- **api_key**: YOUR_API_KEY_HERE (must be replaced with your actual API key)
- **name**: Frank (user identification)
- **model**: Qwen/Qwen3-VL-30B-A3B-Instruct (AI model)

After the config file is created, edit the `api_key` field with your actual API key and run the program again.

> [!WARNING]
> **FOR FUN ONLY - STUDY PURPOSES ONLY**
>
> This tool is intended **FOR EDUCATIONAL AND STUDY PURPOSES ONLY**.
> - Use this tool responsibly and ethically
> - Only use it in environments where automated assistance is permitted
> - This tool is designed to help with learning and understanding, not to unfairly gain advantages
> - Users are responsible for complying with all applicable rules, terms of service, and academic integrity policies
> - The creators assume no responsibility for misuse of this tool

## License

Please refer to the LICENSE file for licensing information.

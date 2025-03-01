import fitz  # PyMuPDF
import cv2
import numpy as np
from PIL import Image


def remove_black_margin_from_image(image):
    """
    Removes black margins from an image using OpenCV.
    Parameters:
    - image (numpy array): The image as a NumPy array.
    
    Returns:
    - cropped_image (numpy array): The cropped image without black margins.
    """
    # Convert to grayscale
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

    # Threshold the image to binary (black and white)
    _, binary = cv2.threshold(gray, 10, 255, cv2.THRESH_BINARY)

    # Find contours to detect non-black areas
    contours, _ = cv2.findContours(binary, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
    if contours:
        # Get the bounding box of the largest contour
        x, y, w, h = cv2.boundingRect(contours[0])
        # Crop the black margins
        cropped_image = image[y:y + h, x:x + w]
        return cropped_image
    else:
        # If no contours found, return the original image
        return image


def process_pdf(input_pdf, output_pdf):
    """
    Processes a PDF to remove black margins from image-based pages.
    Parameters:
    - input_pdf (str): Path to the input PDF.
    - output_pdf (str): Path to save the processed PDF.
    """
    # Open the PDF
    pdf = fitz.open(input_pdf)
    output_images = []

    for page_num in range(len(pdf)):
        page = pdf[page_num]

        # Extract the image from the page
        pix = page.get_pixmap()
        img = np.array(Image.frombytes("RGB", [pix.width, pix.height], pix.samples))

        # Remove black margins from the image
        processed_img = remove_black_margin_from_image(img)

        # Save the processed image to the output list
        output_images.append(Image.fromarray(processed_img))

        print(f"Processed page {page_num + 1} of {len(pdf)}")

    # Save all processed images back to a PDF
    if output_images:
        output_images[0].save(output_pdf, save_all=True, append_images=output_images[1:])

    print(f"Processed PDF saved to: {output_pdf}")


# Example usage
input_pdf = r"D:\Diploma Sem 6\input.pdf"  # Path to the input PDF
output_pdf = r"D:\Diploma Sem 6\output.pdf"  # Path to save the updated PDF

process_pdf(input_pdf, output_pdf)

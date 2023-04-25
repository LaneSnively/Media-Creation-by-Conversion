from PIL import Image


def pixelate_image(input_image_path, output_image_path, new_size=(100, 100)):
    # Open the input image
    image = Image.open(input_image_path)

    # Resize the image to the given dimensions
    pixelated_image = image.resize(new_size, Image.NEAREST)

    # Save the pixelated image to the provided output path (optional)
    pixelated_image.save(output_image_path)

    return pixelated_image


def quantize_image(image, number_of_colors=26):
    # Quantize the image to a fixed number of colors
    quantized_image = image.quantize(colors=number_of_colors)

    # Create a color palette
    color_palette = quantized_image.getpalette()[:number_of_colors * 3]
    color_palette = [tuple(color_palette[i:i + 3]) for i in range(0, len(color_palette), 3)]

    return quantized_image, color_palette


def image_to_text(image, color_palette):
    # Define the alphabet for mapping
    alphabet = "abcdefghijklmnopqrstuvwxyz"

    # Create a color-to-letter mapping
    color_to_letter = {color: letter for color, letter in zip(color_palette, alphabet)}

    # Convert the quantized image back to RGB mode
    rgb_image = image.convert("RGB")

    # Iterate through the pixels and replace colors with letters
    text = ""
    for y in range(rgb_image.height):
        for x in range(rgb_image.width):
            # Get the pixel color
            pixel_color = rgb_image.getpixel((x, y))

            # Map the pixel color to a letter in the alphabet
            text += color_to_letter[pixel_color]

        # Add a newline character to represent rows
        text += "\n"

    return text


if __name__ == "__main__":
    input_image_path = "20220315_182219.jpg"
    output_image_path = "20220315_182219pixelated.jpg"
    pixelated_image = pixelate_image(input_image_path, output_image_path)

    quantized_image, color_palette = quantize_image(pixelated_image)
    text_representation = image_to_text(quantized_image, color_palette)

    # Print the text representation of the pixelated image
    print(text_representation)

    # Save the text representation to a file
    # with open("path/to/your/output/text_file.txt", "w") as text_file:
    #     text_file.write(text_representation)
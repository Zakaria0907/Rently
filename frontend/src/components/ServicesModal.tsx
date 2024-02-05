import { Button } from '@chakra-ui/react';
import { useDisclosure } from '@chakra-ui/hooks';
import {
    Modal,
    ModalOverlay,
    ModalContent,
    ModalHeader,
    ModalFooter,
    ModalBody,
    ModalCloseButton,
} from '@chakra-ui/react';

interface ServicesModalProps {
    isOpen: boolean;
    onOpen?: () => void;
    onClose?: () => void | undefined;
    title?: string;
    children?: React.ReactNode;
};

export default function ServicesModal({ isOpen, onOpen, onClose, title, children }: ServicesModalProps): JSX.Element {
    return (
        <>
            <Modal isOpen={isOpen} onClose={onClose!}>
                <ModalOverlay />
                <ModalContent>
                    <ModalHeader>{title}</ModalHeader>
                    <ModalCloseButton />
                    <ModalBody>
                        {children}
                    </ModalBody>

                    <ModalFooter>
                        <Button colorScheme='blue' mr={3} onClick={onClose}>
                            Close
                        </Button>
                    </ModalFooter>
                </ModalContent>
            </Modal>
        </>
    );
}
